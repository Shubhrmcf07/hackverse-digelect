import face_recognition as fr
import sys

known_path = sys.argv[1]
unknown_path = sys.argv[2]

def compare_faces(known_encoding, unknown_encoding):

    result = fr.compare_faces([known_encoding], unknown_encoding, tolerance=0.7)

    return result


def detect_faces(known_path, unknown_path):

    try:
        
        known_image = fr.load_image_file(known_path)
        unknown_image = fr.load_image_file(unknown_path)
        
        known_encoding = fr.face_encodings(known_image)[0]
        unknown_encoding = fr.face_encodings(unknown_image)[0]
    
    except:
        return "Please retry!"    

    result = compare_faces(known_encoding, unknown_encoding)

    return result[0]


if __name__ == "__main__":

    print(detect_faces(known_path, unknown_path))
