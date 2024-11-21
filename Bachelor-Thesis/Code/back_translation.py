import os, re, json, random, sys
from pprint import pprint
from deep_translator import GoogleTranslator
import pyarrow as pa
import pandas as pd

translator = GoogleTranslator()
pprint(translator.get_supported_languages(as_dict=True))

# Open the Arrow file in read mode
with pa.ipc.open_stream('/home/pkarageorgis/thesis/unfair_tos_data/train/data-00000-of-00001.arrow') as f:
    # Read all of the data from the file
    data = f.read_all()

# Convert the Arrow data to a Pandas DataFrame
df = data.to_pandas()
opath = sys.argv[1]

#languages = ['fr', 'es', 'de']
languages = ['fr']
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
aug_data_dict = {'text': texts, 'labels': labels}

# Create the DataFrame
df_aug = pd.DataFrame(aug_data_dict)

# Set integer data type for label column
df_aug['labels'] = df_aug['labels'].astype(int)
count = 0
count2 = 0
for text in range(len(df)):
    if text % 500 == 0 and text != 0:
        count2 += 500
        print("Completed: ", count2)
    new_row = pd.DataFrame({'text': [df.loc[text, "text"]], 'labels': [df.loc[text, "labels"]]})
    df_aug = pd.concat([df_aug, new_row], ignore_index=True)
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
        new_row = pd.DataFrame({'text': [new_text], 'labels': [df.loc[text, "labels"]]})
        df_aug = pd.concat([df_aug, new_row], ignore_index=True)


print("In total the failed translations were: ", count)
print("The total length of the dataframe is: ", len(df_aug))
df_aug.to_csv(opath, index=False, encoding='utf-8')