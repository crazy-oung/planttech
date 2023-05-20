import cv2
import math

def get_color(rem_file_name) :
    img = cv2.imread(rem_file_name) # 이미지 전처리(배경제거)된 이미지 호출
    img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)

    pixel_cnt = [0, 0, 0] # [Good, Not Bad, Bad] pixel loop counter
    color_cnt = [0, 0, 0] # [Good, Not Bad, Bad] pixel color counter

    for row in img_hsv :
        for pixel in row : # 순서대로 초록색 계열, 노란색 계열 추출, 갈색 계열(h : 0~179)
            if (pixel[0] >= 35 and pixel[0] <= 85) and (pixel[1] >= 20 and pixel[1] <= 255) and (pixel[2] >= 20 and pixel[2] <= 255) :
                pixel_cnt[0] += 1
#                color_cnt[0] += pixel[0]

            elif (pixel[0] >= 6 and pixel[0] <= 34) and (pixel[1] >= 20 and pixel[1] <= 255) and (pixel[2] >= 20 and pixel[2] <= 255) :
                pixel_cnt[1] += 1
#                color_cnt[1] += pixel[0]

            elif (pixel[0] >= 170 or pixel[0] <= 5) and (pixel[1] >= 20 and pixel[1] <= 255) and (pixel[2] >= 20 and pixel[2] <= 255) :
                pixel_cnt[2] += 1
#                color_cnt[2] += pixel[0]

    print(pixel_cnt)

    return pixel_cnt

def get_state(rem_file_name) :
    pixel_cnt = get_color(rem_file_name)

#    s_pixel = sum(pixel_cnt)
#    s_color = sum(color_cnt)
    max_color = max(pixel_cnt)
    avg_color = pixel_cnt.index(max_color)
#    avg_color = round(s_color / s_pixel)
    # 평균 색 반올림(정수로 만들어줌)

    if avg_color == 0 :
        plant_state = "Good"
    elif avg_color == 1 :
        plant_state = "Not Bad"
    else :
        plant_state = "Bad"

#    return avg_color, plant_state
    return plant_state, avg_color

# get_color(img) # 컬러 추출
# avg_color, plant_state = get_state() # 상태 판단

# print("What's your plant color?", avg_color)
# print("What's your plant state?", plant_state)
