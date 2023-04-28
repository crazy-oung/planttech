import random
from datetime import datetime, timedelta
import matplotlib.pyplot as plt


def make_trade_data(num_data):
    # 기본 식물 가격
    base_price = 10000

    # 거래 데이터를 저장할 리스트
    trade_data = []

    for i in range(num_data):
        plant_num = i + 1
        plant_status = random.randint(0, 4)

        if plant_status == 0:
            price = base_price * random.uniform(0.4, 0.6)
            status_str = "very bad"
        elif plant_status == 1:
            price = base_price * random.uniform(0.6, 0.8)
            status_str = "bad"
        elif plant_status == 2:
            price = base_price * random.uniform(0.8, 1.0)
            status_str = "soso"
        elif plant_status == 3:
            price = base_price * random.uniform(1.0, 1.1)
            status_str = "good"
        else:
            price = base_price * random.uniform(1.1, 1.25)
            status_str = "very good"

        # 거래일시 생성
        trade_time = datetime.now() - timedelta(days=random.randint(0, 365))

        price = int(price)
        # 거래 데이터 리스트에 추가
        trade_data.append([plant_num, status_str, price, trade_time])

    return trade_data


def plot_trade_data(trade_data):
    # 상태 목록
    status_list = ["very bad", "bad", "soso", "good", "very good"]

    # 서브플롯 개수
    num_subplots = len(status_list)

    # 그래프 사이즈 설정
    fig, axs = plt.subplots(num_subplots, 1, figsize=(10, 5*num_subplots))

    # 각 상태별로 서브플롯에 그래프 출력
    for i, status in enumerate(status_list):
        # 해당 상태의 데이터 추출
        data = [d for d in trade_data if d[1] == status]

        # 날짜순으로 정렬
        data = sorted(data, key=lambda x: x[3])

        # x, y 값 추출
        x_values = [d[3] for d in data]
        y_values = [d[2] for d in data]

        # 서브플롯에 그래프 출력
        axs[i].plot(x_values, y_values)
        axs[i].set_title(f"{status.capitalize()} Plants")
        axs[i].set_xlabel("Trade Date")
        axs[i].set_ylabel("Price")

    plt.subplots_adjust(top=0.95, bottom=0.05, left=0.05, right=0.95, hspace=0.5)
    plt.show()




# 거래 데이터 생성
trade_data = make_trade_data(100)
print(trade_data)
# 그래프 출력
plot_trade_data(trade_data)
