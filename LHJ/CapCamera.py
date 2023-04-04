import cv2
import socketio
import base64
import datetime

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

# IP 카메라에서 영상을 불러옵니다.
cap = cv2.VideoCapture('rtsp://admin:uoclab2023@192.168.0.36:10554/tcp/av0_0')

while True:
    ret, frame = cap.read()
    if ret:
        # 영상 프레임을 Flask-SocketIO 서버로 전송합니다.
        send_video(frame)
        print('영상전송완료!')

    else:
        break

cap.release()
cv2.destroyAllWindows()