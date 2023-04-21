from sklearn.ensemble import RandomForestRegressor
#from sklearn.metrics import mean_squared_error
import random

# 실제 센서값
get_data = []
data = []
status = []

# 온도 : 0℃ ~ 50℃, 오차: +-2℃
# 습도: 상대습도 20% ~ 90%, 오차: +-5%
# 조도: 0(어두움) ~ 1024(밝음), 일반 방에서의 조도 값: 80 ~ 130
# 물 온도: -55℃ ~ 125℃
# 위의 값에 맞게 배열 데이터 생성
def get_real_data(get_data, data, n):
    for i in range(n):
        plant_num = i + 1
        temp = round(random.uniform(0, 50), 1)
        humidity = round(random.uniform(20, 90), 1)
        light = random.randint(0, 1024)
        water_temp = round(random.uniform(-55, 125), 1)

        get_data.append([plant_num, temp, humidity, light, water_temp])

# 대기온도 정규화
        if 14.25 <= temp <= 26.25:
            temp_norm = 1.0
        elif (1.75 <= temp < 14.25) or (26.25 < temp <= 38.75):
            temp_norm = 0.5
        else:
            temp_norm = 0.0

        # 물 온도 정규화
        if 14.25 <= water_temp <= 26.25:
            water_temp_norm = 1.0
        elif (1.75 <= water_temp < 14.25) or (26.25 < water_temp <= 38.75):
            water_temp_norm = 0.5
        else:
            water_temp_norm = 0.0

        # 습도 정규화
        if 72.5 <= humidity <= 90:
            humidity_norm = 1.0
        elif 37.5 <= humidity < 72.5:
            humidity_norm = 0.5
        else:
            humidity_norm = 0.0

        # 조도 정규화
        if 60 <= light <= 316:
            light_norm = 1.0
        elif (0 <= light < 60) or (316 < light <= 768):
            light_norm = 0.5
        else:
            light_norm = 0.0

        data.append([plant_num, temp_norm, humidity_norm, light_norm, water_temp_norm])

# 4 : 매우 좋음 ~ 0 : 매우 나쁨
def get_status(data, status):
    for plant in data:
        temp_norm, humidity_norm, water_temp_norm, light_norm = plant[1:]

        if temp_norm == humidity_norm == water_temp_norm == 1:
            status.append(4)

        elif (temp_norm, humidity_norm, water_temp_norm).count(1) == 2 and 0.5 in (temp_norm, humidity_norm, water_temp_norm):
            if light_norm >= 0.5:
                status.append(4)
            else:
                status.append(3)

        elif (temp_norm, humidity_norm, water_temp_norm).count(0.5) == 2 and 1 in (temp_norm, humidity_norm, water_temp_norm):
            status.append(3)

        elif (temp_norm, humidity_norm, water_temp_norm).count(1) == 2 and 0 in (temp_norm, humidity_norm, water_temp_norm):
            if light_norm == 1:
                status.append(3)
            else:
                status.append(2)

        elif (temp_norm, humidity_norm, water_temp_norm).count(0.5) == 3:
            status.append(2)

        elif (0 in (temp_norm, humidity_norm, water_temp_norm) and 0.5 in (temp_norm, humidity_norm, water_temp_norm) and 1 in (temp_norm, humidity_norm, water_temp_norm)):
            status.append(2)

        elif (temp_norm, humidity_norm, water_temp_norm).count(0) == 2 and 1 in (temp_norm, humidity_norm, water_temp_norm):
            status.append(1)

        elif (temp_norm, humidity_norm, water_temp_norm).count(0.5) == 2 and 0 in (temp_norm, humidity_norm, water_temp_norm):
            status.append(1)

        elif (temp_norm, humidity_norm, water_temp_norm).count(0) == 2 and 0.5 in (temp_norm, humidity_norm, water_temp_norm):
            if light_norm == 1:
                status.append(1)
            else:
                status.append(0)
        else:
            status.append(0)



get_real_data(get_data, data, 3000)
get_status(data, status)
print(data)
print(status)
# plants 데이터에서 식물 번호를 제외한 데이터만 추출
X = [data[1:] for data in get_data]
# status 데이터
y = status

# Random Forest Classifier 모델 생성
rf = RandomForestRegressor(n_estimators=1000, random_state=42)
rf.fit(X, y)

new_plants = []
normalized_new_plants = []
new_status = []
get_real_data(new_plants, normalized_new_plants, 10)
print("normalized new plant : ",normalized_new_plants)
get_status(normalized_new_plants, new_status)
print("new plant status : ", new_status)
X_test = [data[1:] for data in new_plants]

y_pred = rf.predict(X_test)

print("[", end="")
for i in range(len(y_pred)):
    if i != 0:
        print(", ", end="")
    print("%.2f" % y_pred[i], end="")
print("]")

rounded_y_pred = []
for i in range(len(y_pred)):
    rounded_y_pred.append(round(y_pred[i]))
print(rounded_y_pred)

for i in range(len(rounded_y_pred)):
    if rounded_y_pred[i] == 0:
        print(i+1," 번 식물의 예상 등급 : 매우 나쁨")
    elif rounded_y_pred[i] == 1:
        print(i+1," 번 식물의 예상 등급 : 나쁨")
    elif rounded_y_pred[i] == 2:
        print(i + 1, " 번 식물의 예상 등급 : 보통")
    elif rounded_y_pred[i] == 3:
        print(i+1," 번 식물의 예상 등급 : 좋음")
    elif rounded_y_pred[i] == 4:
        print(i+1," 번 식물의 예상 등급 : 매우 좋음")
    else:
        print("식물의 상태 등급 오류!")
