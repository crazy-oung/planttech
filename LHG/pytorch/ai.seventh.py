import torch
import numpy as np

# 가중치 설정
w = np.array([0.4, 0.3, 0.2, 0.1])

# 입력 데이터 정규화 함수
def min_max_normalize(x):
    min_val = np.min(x)
    max_val = np.max(x)
    x_norm = (x - min_val) / (max_val - min_val)
    return x_norm

# 가중치 사용하여 예측
def predict_with_weight(x, w):
    x_norm = min_max_normalize(x)
    y_pred_norm = net(torch.tensor(x_norm, dtype=torch.float32))
    y_pred = (y_pred_norm.detach().numpy() * (max(y_train) - min(y_train)) + min(y_train)) * w + 50
    return y_pred.item()

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

# 테스트 데이터
x_test = np.array([[87, 87, 94, 96]])

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
    # numpy array를 tensor로 변환
    inputs = torch.tensor(x_train, dtype=torch.float32)
    labels = torch.tensor(y_train, dtype=torch.float32)

    # forward 연산
    outputs = net(inputs)

    # 손실 계산
    loss = criterion(outputs, labels)

    # 역전파
    optimizer.zero_grad()
    loss.backward()
    optimizer.step()

    # 1000번마다 손실 출력
    if (epoch + 1) % 1000 == 0:
        print('Epoch [{}/{}], Loss: {:.4f}'.format(epoch + 1, 10000, loss.item()))

# 테스트
accuracies = []
for i, weight in enumerate(w):
    y_pred = predict_with_weight(x_test, weight)
    acc = 100 * (1 - abs(y_pred - 75) / 75)
    accuracies.append(acc)
    print('Weight {}: {:.1f}, Predicted Output: {:.2f}, Accuracy: {:.2f}%'.format(i+1, weight, y_pred, accuracies[i]))
