import cv2
import socketio
import base64
from datetime import datetime
from threading import Thread

from State import *
from remove_bg import *

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
def get_today_state() : # 하루에 한 번씩 정해진 시간마다 상태 얻기
    rm_bg()
    state = get_state()
    print(state)
    sio.emit('state', state)

# IP 카메라에서 영상을 불러옵니다.
# cap = cv2.VideoCapture('rtsp://admin:uoclab2023@192.168.221.198:10554/tcp/av0_0')

def cap_camera() :

    cap = cv2.VideoCapture(0)
    i = 0

    while True:
        ret, frame = cap.read()
    
        if ret:
    
            height, width = frame.shape[:2]
    
            # 줄일 해상도
            new_height, new_width = height // 2, width // 2
    
            # 프레임의 해상도를 줄임
            frame = cv2.resize(frame, (new_width, new_height))

            send_video(frame)
#            cv2.imwrite(r'C:\Users\user\Desktop\SendCamera\SendCamera\today_plant.png', frame)

        else:
            break

    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__" :
    sio.connect('http://220.68.82.79:4000')     # Nas의 Flask-SocketIO 서버에 연결

    th1 = Thread(target=cap_camera)
    th2 = Thread(target=get_today_state)

    th1.start()

    i = 0
    
    while True :
        now = datetime.now()
        if i == 0 and now.hour == 11 and now.minute == 54 and now.second == 30 :
            # 시간은 나중에 0시 0분 0초로 설정해야함. 지금은 확인차 시간 임의 설정
            th2.start()
            i = 1
