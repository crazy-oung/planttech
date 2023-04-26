import cv2
import math

pixel_cnt = [0, 0, 0] # [Good, Not Bad, Bad] pixel loop counter
color_cnt = [0, 0, 0] # [Good, Not Bad, Bad] pixel color counter

def get_color(img) :
    img_hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    for row in img_hsv :
        for pixel in row : # 순서대로 초록색 계열, 노란색 계열 추출, 갈색 계열(h : 0~179)
            if (pixel[0] >= 45 and pixel[0] <= 70) & (pixel[1] >= 20 and pixel[1] <= 255) & (pixel[2] >= 20 and pixel[2] <= 255) :
                pixel_cnt[0] += 1
                color_cnt[0] += pixel[0]

            elif (pixel[0] >= 15 and pixel[0] <= 44) & (pixel[1] >= 20 and pixel[1] <= 255) & (pixel[2] >= 20 and pixel[2] <= 255) :
                pixel_cnt[1] += 1
                color_cnt[1] += pixel[0]

            elif (pixel[0] <= 14 and pixel[0] >= 170) & (pixel[1] >= 20 and pixel[1] <= 255) & (pixel[2] >= 20 and pixel[2] <= 128) :
                pixel_cnt[2] += 1
                color_cnt[2] += pixel[0]

def get_state() :
    s_pixel = sum(pixel_cnt)
    s_color = sum(color_cnt)

    avg_color = round(s_color / s_pixel)
    # 평균 색 반올림(정수로 만들어줌)

    if avg_color >= 45 and avg_color <= 70 :
        plant_state = "Good"
    elif avg_color >= 15 and avg_color <= 44 :
        plant_state = "Not Bad"
    else :
        plant_state = "Bad"

    return avg_color, plant_state

img = cv2.imread('no-bg.png') # 이미지 전처리(배경제거)된 이미지 호출
get_color(img) # 컬러 추출
avg_color, plant_state = get_state() # 상태 판단

print("What's your plant color?", avg_color)
print("What's your plant state?", plant_state)