# rembg 패키지에서 remove 클래스 불러오기
from rembg import remove 

# PIL 패키지에서 Image 클래스 불러오기
from PIL import Image 

def remove_bg(img_path, file_name, i) :
    # 이미지 파일 불러오기
    img = Image.open(file_name) 
    
    # 배경 제거하기
    out = remove(img) 
    
    # 변경된 이미지 저장하기
    file_name = img_path + '\\rem_test' + str(i) + '.png'
    out.save(file_name)
    
    return file_name