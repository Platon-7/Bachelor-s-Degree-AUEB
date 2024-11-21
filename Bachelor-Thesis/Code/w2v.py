import pyarrow as pa
from nltk.corpus import stopwords
import pandas as pd
from gensim.models import FastText
from pprint import pprint
import os, pickle, re, json, random, sys
from gensim.models.keyedvectors import KeyedVectors
import torch
import nltk
import numpy as np

# Open the Arrow file in read mode
with pa.ipc.open_stream('/home/pkarageorgis/thesis/unfair_tos_data/train/data-00000-of-00001.arrow') as f:
    # Read all of the data from the file
    data = f.read_all()

# Convert the Arrow data to a Pandas DataFrame
df = data.to_pandas()

preprocess_word = lambda word: ''.join('D' if char.isdigit() else char.lower() for char in word)

w2v_embeddings_path = '/home/pkarageorgis/thesis/embeddings&stopwords/Law2Vec.100D.txt'
embeddings = KeyedVectors.load_word2vec_format(w2v_embeddings_path, binary=False)
sws_path = '/home/pkarageorgis/thesis/embeddings&stopwords/stopwords.pkl'

opath = sys.argv[1]

with open(sws_path, 'rb') as f:
    stopwords = pickle.load(f)

texts = []
labels = []
# Create a dictionary with the column data
w2v_aug_data_dict = {'text': texts, 'labels': labels}

# Create the DataFrame
df_aug = pd.DataFrame(w2v_aug_data_dict)

# Set integer data type for label column
df_aug['labels'] = df_aug['labels'].astype(int)
already_known_sim = {}

for text in range(len(df)):
    clean_text = df.loc[text, "text"]
    for tok in clean_text.split():
        prep = preprocess_word(tok)
        if tok.isnumeric():
            continue
        if prep in stopwords: # an einai stopword min kaneis tipota
            continue
        else: # alliws mpes edw mesa
            try:
                tt = already_known_sim[prep] # an to exw psaksei hdh bale to idio to token
            except:
                try: # allios des to similarity, an einai > 0.95 parto
                    already_known_sim[prep] = [t[0] for t in embeddings.most_similar(prep, topn=10) if t[1] > 0.9]
                except: # allios peta to
                    already_known_sim[prep] = []

    #####################################################################################################
    clean_toks = clean_text.split() # olo to text temaxismeno se lekseis
    sents = [clean_text]

    for _ in range(1):
        s = '' # ayto ksekinaei adeio kai ayksanetai synexws, einai ta augmented data
        for tok in clean_toks:
            try: # edw exw merikoys candidates, h einai to idio to token h einai megalytera similarities
                # dialegw sthn tyxh mesw toy random.choice
                prep = preprocess_word(tok)
                cands = already_known_sim[prep] + [prep]
                s += random.choice(cands) + ' '
            except:
                s += tok + ' '
        s = s.strip()
        if s != clean_text and s not in sents:
            sents.append(s)
    #####################################################################################################
    for s in sents:
        new_row = pd.DataFrame({'text': [s], 'labels': [df.loc[text, "labels"]]})
        df_aug = pd.concat([df_aug, new_row], ignore_index=True)


def sum_list_lengths(dictionary):
    total_sum = 0
    for key, value in dictionary.items():
        total_sum += len(value)
    return total_sum

#result = sum_list_lengths(already_known_sim)
#print("Cool method: ", result)
print("The augmented data have length: ", len(df_aug))
df_aug.to_csv(opath, index=False, encoding='utf-8')