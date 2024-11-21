import os, re, json, random, sys
from pprint import pprint
from deep_translator import GoogleTranslator
import pyarrow as pa
import pandas as pd

translator = GoogleTranslator()
#pprint(translator.get_supported_languages(as_dict=True))

# Open the Arrow file in read mode
with pa.ipc.open_stream('/home/pkarageorgis/thesis/scotus_data/train/data-00000-of-00001.arrow') as f:
    # Read all of the data from the file
    data = f.read_all()

# Convert the Arrow data to a Pandas DataFrame
df = data.to_pandas()
opath = sys.argv[1]

# Augmentations
available_augs = []
label_counts = df['label'].value_counts()
max_label_size = max(label_counts)

# Triple the data up to max_label size
for label in range(len(label_counts)):
    available_augs.append(max_label_size - label_counts[label])

languages = ['fr', 'es']
def break_text(text, limit=4000):
    if len(text) <= limit:
        return [text]  # Text is within the limit, no need to break

    separators = ['\n\n', '. ', '\n']  # Separators: paragraph, sentence, line
    result = []
    index = 0

    while index < len(text):
        chunk = text[index:index + limit]

        separation_index = limit
        for separator in separators:
            if separator in chunk:
                separation_index = chunk.rfind(separator, 0, limit) + len(separator)
                break

        result.append(chunk[:separation_index])
        index += separation_index

    return result


texts = []
labels = []
# Create a dictionary with the column data
aug_data_dict = {'text': texts, 'label': labels}

# Create the DataFrame
df_aug = pd.DataFrame(aug_data_dict)

# Set integer data type for label column
df_aug['label'] = df_aug['label'].astype(int)
count = 0
count2 = 0
iterations = None
for text in range(len(df)):
    if text % 50 == 0 and text != 0:
        count2 += 50
        print("Completed: ", count2)
    label = df["label"].loc[text]
    new_row = pd.DataFrame({'text': [df.loc[text, "text"]], 'label': [df.loc[text, "label"]]})
    df_aug = pd.concat([df_aug, new_row], ignore_index=True)
    if available_augs[label] > 0:
        chunks = break_text(df.loc[text, "text"])
        for dest_lang in languages:
            for counter, chunk in enumerate(chunks):
                try:
                    text_in_dest = GoogleTranslator(source='en', target=dest_lang).translate(chunk)
                    text_in_en = GoogleTranslator(source=dest_lang, target='en').translate(text_in_dest)
                    if (chunk != text_in_en):
                        chunks[counter] = text_in_en  
                except Exception as e:
                    print(f"An error occurred: {e}")
                    print("The error was caused in : "+ str(text)+ " and the language "+ dest_lang)
                    count+=1
            new_text = ''.join(chunks)
            new_row = pd.DataFrame({'text': [new_text], 'label': [df.loc[text, "label"]]})
            df_aug = pd.concat([df_aug, new_row], ignore_index=True)
            available_augs[label] -= 1
            if available_augs[label] == 0:
                break

print("In total the failed translations were: ", count)
print("The total length of the dataframe is: ", len(df_aug))
df_aug.to_csv(opath, index=False, encoding='utf-8')