import random
import matplotlib.pyplot as plt
import pandas as pd

data = []
avg_data = []
avg_normalized_data = []
avg_status = []
for plant_num in range(1, 11):
    for day in range(1, 8):
        temp = round(random.uniform(0, 50), 1)
        humidity = round(random.uniform(20, 90), 1)
        light = random.randint(0, 1024)
        water_temp = round(random.uniform(-55, 125), 1)
        plant_age = day

        data.append([plant_num, temp, humidity, light, water_temp, plant_age])



print(data)


def get_averaged_data(data):
    for plant_num in range(1, 11):
        for age in range(1, 8):
            age_data = []
            for day_data in data:
                if day_data[0] == plant_num and day_data[5] <= age:
                    age_data.append(day_data[1:5])
            if len(age_data) > 0:
                age_averaged_info = [plant_num, age] + list(map(lambda x: round(sum(x) / len(x), 1), zip(*age_data)))
                avg_data.append(age_averaged_info)

    return avg_data

get_averaged_data(data)
print("avg data : ",avg_data)

def normalize_sensor_data(data):
    for sensor_data in data:
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
print("avg norm data : ",avg_normalized_data)


def get_status(data, status):
    for plant in data:
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

print(avg_status)


df = pd.DataFrame(avg_status, columns=['plant_id', 'plant_age', 'plant_status', 'temp_norm', 'humidity_norm', 'water_temp_norm', 'light_norm'])

status_dict = {0: 'Very bad', 1: 'Bad', 2: 'Normal', 3: 'Good', 4: 'Very good'}

plant_ids = df['plant_id'].unique()

dfs = []
for plant_id in plant_ids:
    plant_df = df[df['plant_id'] == plant_id].reset_index(drop=True)
    dfs.append(plant_df)

fig, axs = plt.subplots(len(dfs), 1, figsize=(10, 20))
plt.subplots_adjust(hspace=0.5)

for i, plant_df in enumerate(dfs):
    axs[i].plot(range(1, len(plant_df) + 1), plant_df['plant_status'], 'o-')
    axs[i].set_xticks(range(1, len(plant_df) + 1))
    axs[i].set_yticks(range(5))
    axs[i].set_yticklabels([status_dict[i] for i in range(5)])
    axs[i].set_title(f'Plant {plant_df["plant_id"].iloc[0]}')
    axs[i].set_ylim([-0.5, 4.5])
    axs[i].set_xlabel('Day')
    axs[i].set_ylabel('Status')

plt.tight_layout()
plt.show()
