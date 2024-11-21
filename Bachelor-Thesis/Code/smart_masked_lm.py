from transformers import AutoTokenizer, AutoModelForMaskedLM
import torch
from transformers import pipeline
import pyarrow as pa
import sys
import pandas as pd
import random

# Open the Arrow file in read mode
with pa.ipc.open_stream('/home/pkarageorgis/thesis/scotus_data/train/data-00000-of-00001.arrow') as f:
    # Read all of the data from the file
    data = f.read_all()

# Convert the Arrow data to a Pandas DataFrame
df = data.to_pandas()

model_name = "nlpaueb/legal-bert-base-uncased"

tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForMaskedLM.from_pretrained(model_name)
opath = sys.argv[1]

# Augmentations
available_augs = []
label_counts = df['label'].value_counts()
max_label_size = max(label_counts)

# Triple the data up to max_label size
for label in range(len(label_counts)):
    available_augs.append(max_label_size - label_counts[label])

print(available_augs)
def break_text(text, limit=500):
    separators = ['\n\n', '. ', '\n']  # Separators: paragraph, sentence, line
    chunks = []
    
    while len(text) > limit:
        # Find the next separator within the limit
        next_separator = None
        for separator in separators:
            if separator in text[:limit]:
                next_separator = separator
                break
        
        if next_separator is None:
            # No separator found within the limit, truncate the text
            chunk = text[:limit]
            text = text[limit:]
        else:
            # Found a separator, split the text and update
            split_index = text.find(next_separator) + len(next_separator)
            chunk = text[:split_index]
            text = text[split_index:]
        
        chunks.append(chunk)
    
    # Append the remaining text as the last chunk
    if text:
        chunks.append(text)
    
    return chunks

texts = []
labels = []
aug_data_dict = {'text': texts, 'label': labels}
df_aug = pd.DataFrame(aug_data_dict)
df_aug['label'] = df_aug['label'].astype(int)

count = 0
for text in range(len(df)):
    if text%500 == 0 and text!=0:
        print(5000-text, "to go")
    label = df["label"].loc[text]
    new_row = pd.DataFrame({'text': [df.loc[text, "text"]], 'label': [df.loc[text, "label"]]})
    df_aug = pd.concat([df_aug, new_row], ignore_index=True)
    for i in range(2):
        if available_augs[label] > 0:
            chunks = break_text(df.loc[text, "text"])
            for j, sentence in enumerate(chunks):

                # Tokenize the sentence
                inputs = tokenizer(sentence, return_tensors="pt")
                input_ids = inputs["input_ids"]

                # Generate a random index to mask
                if len(input_ids[0]) > 2:
                    masked_index = random.randint(1, len(input_ids[0]) - 2)  # Avoid special tokens [CLS] and [SEP]
                else:
                    continue

                # Create a copy of the input and mask the token
                masked_input = input_ids.clone()
                masked_input[0, masked_index] = tokenizer.mask_token_id

                # Get the predicted token and its probability
                with torch.no_grad():
                    logits = model(masked_input).logits
                mask_token_logits = logits[0, masked_index]
                predicted_token = torch.argmax(mask_token_logits).item()
                predicted_probability = torch.softmax(mask_token_logits, dim=-1)[predicted_token].item()

                # Replace the best predicted token in the input as long as:
                # 1) It has a probability > 0.7
                if predicted_probability >= 0.7:
                    masked_input[0, masked_index] = predicted_token
                chunks[j] = tokenizer.decode(masked_input[0][1:-1])
            new_text = ''.join(chunks)
            new_row = pd.DataFrame({'text': [new_text], 'label': [df.loc[text, "label"]]})
            df_aug = pd.concat([df_aug, new_row], ignore_index=True)
            available_augs[label] -= 1
            if available_augs[label] == 0:
                break
        else:
            break

print("Length", len(df_aug))
df_aug.to_csv(opath, index=False, encoding='utf-8')