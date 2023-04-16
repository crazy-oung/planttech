import torch
import torch.nn as nn
import torch.optim as optim
import numpy as np

# 데이터 생성
# 물 온도, 대기 온도, 습도, 조도(광량) 센서값
# 정답인 콩나물 길이는 4개의 센서값의 합으로 결정됨.
# 높은 센서값일수록 길이가 길어지도록 만듦.
np.random.seed(1)
x_train = np.random.randint(0, 100, size=(6, 4))
y_train = np.sum(x_train, axis=1)

# min-max 정규화 함수
def min_max_normalize(data):
    numerator = data - np.min(data, 0)
    denominator = np.max(data, 0) - np.min(data, 0)
    return numerator / (denominator + 1e-7)

# 데이터 정규화
x_train_norm = min_max_normalize(x_train)
y_train_norm = min_max_normalize(y_train.reshape(-1, 1))

# 파이토치 모델 정의
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.fc1 = nn.Linear(4, 16)
        self.fc2 = nn.Linear(16, 8)
        self.fc3 = nn.Linear(8, 1)

    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.relu(self.fc2(x))
        x = self.fc3(x)
        return x

# 모델 생성
net = Net()

# 손실 함수와 최적화 함수 정의
criterion = nn.MSELoss()
optimizer = optim.SGD(net.parameters(), lr=0.01)

# 학습 시작
for epoch in range(10000):
    inputs = torch.tensor(x_train_norm, dtype=torch.float32)
    labels = torch.tensor(y_train_norm, dtype=torch.float32)

    optimizer.zero_grad()
    outputs = net(inputs)
    loss = criterion(outputs, labels)
    loss.backward()
    optimizer.step()

    if epoch % 1000 == 0:
        print(f"epoch {epoch}, loss {loss.item():.4f}")

# 예측 시작
x_test = np.random.randint(0, 100, size=(1, 4))
x_test_norm = min_max_normalize(x_test)
y_pred_norm = net(torch.tensor(x_test_norm, dtype=torch.float32)).detach().numpy()
y_pred = y_pred_norm * (np.max(y_train) - np.min(y_train)) + np.min(y_train)
print(f"input: {x_test}, predicted output: {y_pred[0]:.4f}")


