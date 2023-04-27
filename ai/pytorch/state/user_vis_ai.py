import random
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.ensemble import RandomForestRegressor

PLANT_NUM = 5


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



get_real_data(get_data, data, 300)
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


new_data = []
avg_data = []
avg_normalized_data = []
avg_status = []
new_avg_status = []

#num_days가 사용자가 이 서비스를 사용한 날의 범위라고 생각하면 말이 맞긴함
def generate_data(num_plants, num_days):
    for plant_num in range(1, num_plants+1):
        plant_age = 0

        existing_plant_nums = [new_data[i][0] for i in range(len(new_data))]
        if plant_num in existing_plant_nums:
            continue

        day = random.randint(1, num_days)

        for day in range(1, day+1):

            temp = round(random.uniform(0, 50), 1)
            humidity = round(random.uniform(20, 90), 1)
            light = random.randint(0, 1024)
            water_temp = round(random.uniform(-55, 125), 1)
            plant_age = day

            new_data.append([plant_num, plant_age, temp, humidity, light, water_temp])

    return new_data

generate_data(PLANT_NUM, 40)



def get_averaged_data(new_data, num_plants):

    for plant_num in range(1, num_plants+1):
        plant_age_list = []
        for i in range(len(new_data)):
            if new_data[i][0] == plant_num:
                plant_age_list.append(new_data[i][1])

        for age in range(1, max(plant_age_list)+1):
            age_data = []
            for day_data in new_data:
                if day_data[0] == plant_num and day_data[1] <= age:
                    age_data.append(day_data[2:6])
            if len(age_data) > 0:
                age_averaged_info = [plant_num, age] + list(map(lambda x: round(sum(x) / len(x), 1), zip(*age_data)))
                avg_data.append(age_averaged_info)

    return avg_data

get_averaged_data(new_data, PLANT_NUM)

def normalize_sensor_data(new_data):
    for sensor_data in new_data:
        plant_num, age, temp, humidity, light, water_temp = sensor_data

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

        avg_normalized_data.append([plant_num, temp_norm, humidity_norm, light_norm, water_temp_norm, age])

    return avg_normalized_data

normalize_sensor_data(avg_data)


def get_status(new_data, status):
    for plant in new_data:
        plant_id, temp_norm, humidity_norm, water_temp_norm, light_norm, plant_age = plant

        if temp_norm == humidity_norm == water_temp_norm == 1:
            plant_status = 4

        elif (temp_norm, humidity_norm, water_temp_norm).count(1) == 2 and 0.5 in (temp_norm, humidity_norm, water_temp_norm):
            if light_norm >= 0.5:
                plant_status = 4
            else:
                plant_status = 3

        elif (temp_norm, humidity_norm, water_temp_norm).count(0.5) == 2 and 1 in (temp_norm, humidity_norm, water_temp_norm):
            plant_status = 3

        elif (temp_norm, humidity_norm, water_temp_norm).count(1) == 2 and 0 in (temp_norm, humidity_norm, water_temp_norm):
            if light_norm == 1:
                plant_status = 3
            else:
                plant_status = 2

        elif (temp_norm, humidity_norm, water_temp_norm).count(0.5) == 3:
            plant_status = 2

        elif (0 in (temp_norm, humidity_norm, water_temp_norm) and 0.5 in (temp_norm, humidity_norm, water_temp_norm) and 1 in (temp_norm, humidity_norm, water_temp_norm)):
            plant_status = 2

        elif (temp_norm, humidity_norm, water_temp_norm).count(0) == 2 and 1 in (temp_norm, humidity_norm, water_temp_norm):
            plant_status = 1

        elif (temp_norm, humidity_norm, water_temp_norm).count(0.5) == 2 and 0 in (temp_norm, humidity_norm, water_temp_norm):
            plant_status = 1

        elif (temp_norm, humidity_norm, water_temp_norm).count(0) == 2 and 0.5 in (temp_norm, humidity_norm, water_temp_norm):
            if light_norm == 1:
                plant_status = 1
            else:
                plant_status = 0
        else:
            plant_status = 0

        status.append([plant_id, plant_age, plant_status, temp_norm, humidity_norm, water_temp_norm, light_norm])


get_status(avg_normalized_data, avg_status)


print("avg new plant : ", avg_data)
print("avg normalized data : ", avg_normalized_data)
print("avg plants status : ", avg_status)

X_test = [data[2:6] for data in new_data]

y_pred = rf.predict(X_test)

new_avg_status = avg_status
for i in range(len(new_avg_status)):
    new_avg_status[i][2] = y_pred[i]

print("new avg plants status : ", new_avg_status)

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







df = pd.DataFrame(avg_status, columns=['plant_id', 'plant_age', 'plant_status', 'temp_norm', 'humidity_norm', 'water_temp_norm', 'light_norm'])

status_dict = {0: 'Very bad', 1: 'Bad', 2: 'Normal', 3: 'Good', 4: 'Very good'}

plant_ids = df['plant_id'].unique()

dfs = []
for plant_id in plant_ids:
    plant_df = df[df['plant_id'] == plant_id].reset_index(drop=True)
    dfs.append(plant_df)

max_length = max([len(plant_df) for plant_df in dfs]) + 5  # 가장 긴 꺾은선 그래프의 길이에 5를 더한 값을 x축의 끝값으로 설정

fig, axs = plt.subplots(1, 1, figsize=(10, 5))

for i, plant_df in enumerate(dfs):
    axs.plot(range(1, len(plant_df) + 1), plant_df['plant_status'], 'o-', label=f'Plant {plant_df["plant_id"].iloc[0]}')

axs.set_xticks(range(1, max_length))
axs.set_yticks(range(5))
axs.set_yticklabels([status_dict[i] for i in range(5)])
axs.set_title('Plant Status')
axs.set_ylim([-0.5, 4.5])
axs.set_xlabel('Day')
axs.set_ylabel('Status')
axs.legend()

plt.tight_layout()
plt.show()
