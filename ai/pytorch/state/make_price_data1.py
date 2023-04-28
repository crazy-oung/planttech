import random
from datetime import datetime, timedelta
import matplotlib.pyplot as plt
import pandas as pd

trade_data = []
def generate_trade_data(n):
    plant_status_prices = {
        0: (0.4, 0.6),
        1: (0.6, 0.8),
        2: (0.8, 1.0),
        3: (1.0, 1.1),
        4: (1.1, 1.25)
    }
    for i in range(n):
        plant_num = random.randint(1, 10)
        plant_status = random.randint(0, 4)
        base_price = 10000
        price_range = plant_status_prices[plant_status]
        price = base_price * random.uniform(price_range[0], price_range[1])
        trade_date = datetime.now() - timedelta(days=random.randint(1, 365))
        trade_data.append([plant_num, plant_status, price, trade_date])
    return trade_data

generate_trade_data(10)
print(trade_data)


def plot_price_trend(data):
    # 데이터를 날짜순으로 정렬합니다.
    sorted_data = sorted(data, key=lambda x: x[3])

    # 각각의 식물 상태에 대해 그래프를 그립니다.
    for status in range(5):
        status_data = [x for x in sorted_data if x[1] == status]
        if status_data:
            # 기본 식물가격을 10000으로 설정합니다.
            base_price = 10000

            # 상태별 가격 변동폭을 설정합니다.
            if status == 0:
                min_multiplier, max_multiplier = 0.4, 0.6
            elif status == 1:
                min_multiplier, max_multiplier = 0.6, 0.8
            elif status == 2:
                min_multiplier, max_multiplier = 0.8, 1.0
            elif status == 3:
                min_multiplier, max_multiplier = 1.0, 1.1
            else:
                min_multiplier, max_multiplier = 1.1, 1.25

            # 가격 데이터를 계산합니다.
            prices = []
            for i in range(len(status_data)):
                price = base_price * (min_multiplier + (max_multiplier - min_multiplier) * i / len(status_data))
                prices.append(price)

            # 그래프를 그립니다.
            df = pd.DataFrame({'date': [x[3] for x in status_data], 'price': prices})
            plt.plot(df['date'], df['price'], label='Status {}'.format(status))

    plt.legend()
    plt.xlabel('Date')
    plt.ylabel('Price')
    plt.show()

plot_price_trend(trade_data)
