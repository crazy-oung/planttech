from datetime import datetime, timedelta
import random
from sklearn.ensemble import RandomForestRegressor
import matplotlib.pyplot as plt

# list to store trade data
trade_data = []

# generate trade data
def make_trade_data(num_data):
    # base plant price
    base_price = 10000

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

        # generate trade datetime
        trade_time = datetime.now() - timedelta(days=random.randint(0, 365))

        price = int(price)
        # add trade data to the list
        trade_data.append([plant_num, status_str, price, trade_time])

    return trade_data

def plot_trade_data(trade_data):
    # 상태 목록
    status_list = ["very bad", "bad", "soso", "good", "very good"]

    # 그래프 사이즈 설정
    fig, axs = plt.subplots(1, 1, figsize=(10, 5))

    # 각 상태별로 데이터 추출하여 서브플롯에 그래프 출력
    for status in status_list:
        # 해당 상태의 데이터 추출
        data = [d for d in trade_data if d[1] == status]

        # 날짜순으로 정렬
        data = sorted(data, key=lambda x: x[3])

        # x, y 값 추출
        x_values = [d[3] for d in data]
        y_values = [d[2] for d in data]

        # 서브플롯에 그래프 출력
        axs.plot(x_values, y_values, label=status.capitalize())

    axs.set_title("Price Trend of Plants")
    axs.set_xlabel("Trade Date")
    axs.set_ylabel("Price")
    axs.legend()

    plt.show()



# generate trade data
#서버로 보내야될 거래데이터
trade_data = make_trade_data(1000)

print("trade_data: ",trade_data)
# 그래프 출력
#plot_trade_data(trade_data)

# dictionary to store trade data grouped by status
grouped_data = {}

# group plants with the same status together
for plant in trade_data:
    status = plant[1]
    if status in grouped_data:
        grouped_data[status].append(plant)
    else:
        grouped_data[status] = [plant]

# dictionary to store predicted prices for each status
predicted_prices = {}

# define the minimum and maximum prices for each status
min_prices = {
    "very bad": 4000,
    "bad": 6000,
    "soso": 8000,
    "good": 10000,
    "very good": 11000
}

max_prices = {
    "very bad": 6000,
    "bad": 8000,
    "soso": 10000,
    "good": 11000,
    "very good": 12500
}

# loop through each status and predict prices for the next 7 days
for status in grouped_data.keys():
    # get the trade data for the current status
    data = grouped_data[status]
    # sort the data by trade time
    data.sort(key=lambda x: x[3])

    # extract the latest price and date
    latest_price = data[-1][2]
    latest_date = data[-1][3]

    # create a list of dates for the next 7 days
    date_list = [latest_date + timedelta(days=i+1) for i in range(7)]

    # dictionary to store predicted prices for each status
    predicted_prices = {}

    # loop through each status and predict prices for the next 7 days
    for status in grouped_data.keys():
        # get the trade data for the current status
        data = grouped_data[status]
        # sort the data by trade time
        data.sort(key=lambda x: x[3])

        # extract the latest price and date
        latest_price = data[-1][2]
        latest_date = data[-1][3]

        # create a list of dates for the next 7 days
        date_list = [latest_date + timedelta(days=i + 1) for i in range(7)]

        # create a list to store the predicted prices
        predicted_prices[status] = {}

        # fit a random forest model to the trade data for the current status
        status_data = [d for d in data if d[1] == status]
        x = [list(map(int, [(plant[3] - latest_date).days, int(plant[3].weekday() < 5)])) for plant in status_data]
        y = [plant[2] for plant in status_data]

        model = RandomForestRegressor(n_estimators=100, random_state=42)
        model.fit(x, y)

        # loop through each date and generate a price prediction using random forest
        for i, date in enumerate(date_list):
            # create a feature vector for the current date
            feature_vector = [
                (date - latest_date).days,  # days since the latest trade
                int(date.weekday() < 5)  # 1 if weekday, 0 if weekend
            ]

            # predict the price for the current date
            predicted_price = model.predict([feature_vector])[0]

            # apply the minimum and maximum prices for the current status
            min_price = min_prices[status]
            max_price = max_prices[status]
            predicted_price = min(max_price, max(min_price, predicted_price))

            # store the predicted price with the corresponding day information
            predicted_prices[status][i + 1] = [date.strftime('%Y-%m-%d'), predicted_price]


def plot_predicted_prices(predicted_prices):
    # 상태 목록
    status_list = ["very bad", "bad", "soso", "good", "very good"]

    # 서브플롯 개수
    num_subplots = len(status_list)

    # 그래프 사이즈 설정
    fig, axs = plt.subplots(num_subplots, 1, figsize=(10, 5*num_subplots))

    # 각 상태별로 서브플롯에 그래프 출력
    for i, status in enumerate(status_list):
        # 해당 상태의 예측 가격 데이터 추출
        prices = predicted_prices[status]

        # x, y 값 추출
        x_values = list(prices.keys())
        y_values = [price[1] for price in prices.values()]

        # 상태별 최댓값과 최솟값 가져오기
        min_price = min_prices[status]
        max_price = max_prices[status]

        # 서브플롯에 그래프 출력
        axs[i].plot(x_values, y_values)
        axs[i].set_title(f"{status.capitalize()} Plants")
        axs[i].set_xlabel("Days")
        axs[i].set_ylabel("Price")
        axs[i].set_ylim([min_price, max_price])

    plt.subplots_adjust(top=0.95, bottom=0.05, left=0.05, right=0.95, hspace=0.5)
    plt.show()



print(predicted_prices)
plot_predicted_prices(predicted_prices)

def plot_price_distribution(data, status):
    # 주말과 주중의 상태별 거래가격 분포 계산
    weekend_prices = {'very bad': [], 'bad': [], 'soso': [], 'good': [], 'very good': []}
    weekday_prices = {'very bad': [], 'bad': [], 'soso': [], 'good': [], 'very good': []}
    for i, plant in enumerate(data):
        price = plant[2]  # 거래 가격 가져오기

        # 날짜 정보를 통해 요일 판별
        date_obj = plant[3]  # 거래 날짜
        print("date_obj: ",date_obj)
        weekday = date_obj.weekday()  # 0: 월요일, 1: 화요일, ..., 6: 일요일

        if weekday >= 5:  # 주말인 경우
            if status[i] in weekend_prices:
                weekend_prices[status[i]].append(price)
        else:  # 주중인 경우
            if status[i] in weekday_prices:
                weekday_prices[status[i]].append(price)

    # 주말과 주중의 상태별 거래가격 분포 플롯
    fig, axes = plt.subplots(nrows=2, ncols=5, figsize=(15, 6), sharey=True)
    fig.suptitle("Plant Price Distribution")

    status_labels = ['very bad', 'bad', 'soso', 'good', 'very good']
    for i in range(5):
        ax1 = axes[0, i]
        ax2 = axes[1, i]

        ax1.hist(weekend_prices[status_labels[i]], bins=10, color='lightblue', edgecolor='black')
        ax1.set_title(f"Weekend - {status_labels[i]}")
        ax1.set_xlabel("Price")
        ax1.set_ylabel("Frequency")

        ax2.hist(weekday_prices[status_labels[i]], bins=10, color='lightgreen', edgecolor='black')
        ax2.set_title(f"Weekday - {status_labels[i]}")
        ax2.set_xlabel("Price")
        ax2.set_ylabel("Frequency")

    plt.tight_layout()
    plt.show()

plot_price_distribution(trade_data, [plant[1] for plant in trade_data])
