# Data Augmentation in Legal Document Classification

## Brief Summary

We use data augmentation techniques to create artificial data and show their effectiveness in improving model performance, focusing on the legal domain. Leveraging LexGLUE results, where LexGLUE is a legal benchmark dataset (information available at https://github.com/coastalcph/lex-glue), we apply various augmentation methods. Our experiments are conducted using LEGAL-BERT, a variation of BERT pre-trained in legal data, recognized as the best-performing model in LexGLUE.

### Augmentation Techniques

-> **Word2Vec**

Word2Vec is an embedding technique that transforms words into high-dimensional vectors in a continuous vector space. It captures semantic relationships between words by representing them in a way that preserves their contextual information. We leverage its ability to capture semantic relationships in order to perform augmentation.

-> **Masked-LM**

Masked-LM is a language model pretraining technique where certain words in a text are masked, and the model is trained to predict these masked words based on their context. We use this to change a number of words in the initial text. The different outputs of the model, prompt us to use it as a data augmentation technique.

-> **Back-Translation**

Back-translation is an augmentation method that involves translating a sentence from one language to another and then translating it back. This process introduces diverse sentence variations, leveraging the inherent linguistic nuances captured during translation, therefore enhancing the training dataset for improved model performance. We use French, German, and Spanish as the pivot languages.

## How-To Guide

### General Instructions

In case you want to reproduce our results all you have to do is follow the instructions of Ilias Chalkidis and the co-authors of LexGLUE (link given above). Replace the original data with the augmented and re-run the experiments.

Note #1: Instead of using the file 'scotus.py' of LexGLUE, use the modified version uploaded in this repository. The reason is that we freeze the layers and also apply gradient clipping to save computation time, which means that if you use 'scotus.py' of the
other repository the results will be different. Also, to compare fairly the results, you have to re-run the experiments with the original data using the frozen model with enforced gradient clipping.


Note #2: The part of 'scotus.py' that you will have to change in order to run all the experiments, is where the data are loaded (there are comments there to guide you). 

### How to Create the Augmented Data

In order to acquire the augmented data, we downloaded the original data locally, following Ilias' instructions, and then ran the corresponding file (etc 'w2v.py') passing the copy of the original data as an input (in the form of a parquet file). The Python files that create the augmented data, export them in CSV format, so we make sure to load them in 'scotus.py' in a corresponding manner (we already did that for you).

## Future Work

Currently, we are performing experiments for two more LexGLUE datasets, ECtHR and UNFAIR-TOS. We hope to get a better idea of the impact data augmentation has in the legal domain, as our results so far concern only one dataset. We aim to publish this work once these experiments are complete.
