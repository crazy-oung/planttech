import torch
import numpy as np

# 가중치 설정
w1 = 0.4
w2 = 0.3
w3 = 0.2
w4 = 0.1


# 입력 데이터 정규화 함수
def min_max_normalize(x):
    min_val = np.min(x)
    max_val = np.max(x)
    x_norm = (x - min_val) / (max_val - min_val)
    return x_norm


# 학습 데이터
x_train = np.array([[70, 80, 40, 60],
                    [80, 70, 60, 50],
                    [50, 40, 30, 60],
                    [40, 60, 90, 80],
                    [80, 90, 60, 70],
                    [30, 70, 60, 80],
                    [50, 60, 80, 90],
                    [60, 80, 70, 40]])

y_train = np.array([[70], [80], [40], [60], [90], [60], [80], [75]])


# 모델 정의
class Net(torch.nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.fc = torch.nn.Linear(4, 1)

    def forward(self, x):
        x = self.fc(x)
        return x


net = Net()

# 손실 함수 정의
criterion = torch.nn.MSELoss()

# 최적화 함수 정의
optimizer = torch.optim.SGD(net.parameters(), lr=0.01)

# 학습
for epoch in range(10000):
    # 입력 데이터 정규화
    x_train_norm = min_max_normalize(x_train)

    # numpy array를 tensor로 변환
    inputs = torch.tensor(x_train_norm, dtype=torch.float32)
    labels = torch.tensor(y_train, dtype=torch.float32)

    # forward 연산
    outputs = net(inputs)

    # 손실 계산
    loss = criterion(outputs, labels)

    # 역전파
    optimizer.zero_grad()
    loss.backward()
    optimizer.step()

    # 100번마다 손실 출력
    if (epoch + 1) % 1000 == 0:
        print('Epoch [{}/{}], Loss: {:.4f}'.format(epoch + 1, 10000, loss.item()))

# 테스트
x_test = np.array([[87, 87, 94, 96]])
x_test_norm = min_max_normalize(x_test)
y_pred_norm = net(torch.tensor(x_test_norm, dtype=torch.float32))
y_pred = (y_pred_norm.detach().numpy() * (max(y_train) - min(y_train)) + min(y_train)) * (100 * w1 + 100 * w2 + 100 * w3 + 100 * w4) + 50

print('Input: {}, Predicted Output: {:.4f}, Expected Output: {:.4f}, Accuracy: {:.2f}%'
      .format(x_test, y_pred.item(), 75, 100 * (1 - abs(y_pred.item() - 75) / 75)))
