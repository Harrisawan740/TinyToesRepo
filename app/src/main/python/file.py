import pandas as pd
import matplotlib.pyplot as plt
import librosa
import librosa.display
import numpy as np

def func ():
    filename = "D:/Related to FYP/Dataset/donateacry-corpus/donateacry_corpus_cleaned_and_updated_data/hungry/0a983cd2-0078-4698-a048-99ac01eb167a-1433917038889-1.7-f-04-hu.wav"
    audio, sample_rate = librosa.load(filename, res_type='kaiser_fast') 
    mfccs_features = librosa.feature.mfcc(y=audio, sr=sample_rate, n_mfcc=40)
    mfccs_scaled_features = np.mean(mfccs_features.T,axis=0)
#     print(mfccs_scaled_features.shape)
    mfccs_scaled_features=mfccs_scaled_features.reshape(1,-1)
#     print(mfccs_scaled_features)
    # temp = np.argmax(model.predict(mfccs_scaled_features),axis=1)
    # predicted_label=model.predict(mfccs_scaled_features)
    # print(predicted_label)
    # print(temp)
    return mfccs_scaled_features