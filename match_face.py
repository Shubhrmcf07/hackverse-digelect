import face_recognition as fr
import numpy as np
from PIL import Image
import io
import requests
import sys

known_path = sys.argv[1]
unknown_path = sys.argv[2]

def compare_faces(known_encoding, unknown_encoding, tolerance):

    result = fr.compare_faces([known_encoding], unknown_encoding, tolerance=tolerance)

    return result

def im2numpy(url):
    
    response = requests.get(url)
    img = Image.open(io.BytesIO(response.content))
    numpydata = np.asarray(img)
    return numpydata

def detect_faces(known_path, unknown_path):
        
    known_image = im2numpy(known_path)
    unknown_image = im2numpy(unknown_path)
    known_encoding = fr.face_encodings(known_image)[0]
    unknown_encoding = fr.face_encodings(unknown_image)[0]
   

    result = compare_faces(known_encoding, unknown_encoding, 0.75)

    return result[0]


if __name__ == "__main__":

    print(detect_faces(known_path, unknown_path))