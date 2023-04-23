import cv2
import socketio
import base64

# Flask-SocketIO 클라이언트 생성
sio = socketio.Client()

# Flask-SocketIO 서버와 연결될 때 호출되는 이벤트 핸들러
@sio.event
def connect():
    print('connected to server')

# Flask-SocketIO 서버로부터 메시지를 받았을 때 호출되는 이벤트 핸들러
@sio.event
def message(data):
    print('received message: ' + data)

# Flask-SocketIO 서버로 영상을 전송하는 함수
def send_video(frame):
    # 영상 프레임을 base64로 인코딩하여 문자열로 변환합니다.
    _, buffer = cv2.imencode('.jpg', frame)
    data = base64.b64encode(buffer).decode('utf-8')

    # 'video' 이벤트를 사용하여 영상 데이터를 Flask-SocketIO 서버로 전송합니다.
    sio.emit('video1', data)

# Flask-SocketIO 서버에 연결합니다.
sio.connect('http://220.68.82.79:4000')

lower_orange = (100, 200, 200)
upper_orange = (140, 255, 255)

lower_green = (30, 80, 100)
upper_green = (80, 255, 255)

lower_blue = (0, 180, 55)
upper_blue = (20, 255, 200)

# IP 카메라에서 영상을 불러옵니다.
# cap = cv2.VideoCapture('rtsp://admin:uoclab2023@192.168.221.198:10554/tcp/av0_0')
cap = cv2.VideoCapture(1)

while True:
    ret, frame = cap.read()

    if ret:

        height, width = frame.shape[:2]

        # 줄일 해상도
        new_height, new_width = height // 2, width // 2

        # 프레임의 해상도를 줄임
        frame = cv2.resize(frame, (new_width, new_height))

        # BGR to HSV 변환
        frame_hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

        # 색상 범위를 제한하여 mask 생성
        frame_mask = cv2.inRange(frame_hsv, lower_green, upper_green)

# 원본 이미지를 가지고 Object 추출 이미지로 생성
        frame_result = cv2.bitwise_and(frame, frame, mask=frame_mask)

        # 영상 프레임을 Flask-SocketIO 서버로 전송합니다.
        send_video(frame_result)

    else:
        break

cap.release()
cv2.destroyAllWindows()