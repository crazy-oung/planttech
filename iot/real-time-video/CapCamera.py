import cv2
import socketio
import base64
from datetime import datetime
from threading import Thread
import time
import schedule
import requests
import json

from State import *
from remove_bg import *

sio = socketio.Client()

 # Flask-SocketIO 서버와 연결될 때 호출되는 이벤트 핸들러
@sio.event
def connect():
    print('connected to server')

 # Flask-SocketIO 서버로부터 메시지를 받았을 때 호출되는 이벤트 핸들러
@sio.event
def message(data):
    print('received message: ' + data)

class camera :
    frame = None
    frame2 = None
    state = None
    state2 = None
    now = datetime.now()

    # Flask-SocketIO 서버로 영상을 전송하는 함수
    def send_video(self, frame, frame2):
        if frame is not None :
            # 영상 프레임을 base64로 인코딩하여 문자열로 변환합니다.
            _, buffer = cv2.imencode('.jpg', frame)
            data = base64.b64encode(buffer).decode('utf-8')

            # 'video' 이벤트를 사용하여 영상 데이터를 Flask-SocketIO 서버로 전송합니다.
            sio.emit('video1', data)

        if frame2 is not None :
            _, buffer = cv2.imencode('.jpg', frame2)
            data = base64.b64encode(buffer).decode('utf-8')

            # 'video' 이벤트를 사용하여 영상 데이터를 Flask-SocketIO 서버로 전송합니다.
            sio.emit('camera2', data)

    def get_today_state(self) : # 하루에 한 번씩 정해진 시간마다 상태 얻기

         # self.capture()
         rm_bg()
         self.state, avg_color = get_state()
         print(self.state)
         print(avg_color)

         sio.emit('state', self.state)
         #self.send_api()

         self.flag = 1

    def send_api(self) : # api로 식물 상태 데이터 보내는 함수

        url = "http://dayounghan.com/v0.2/api/ai-data/pic"

        headers = {"Content-Type": "application/json"}

        state = json.dumps(self.state)
        response = requests.post(url, headers=headers, data=state)

        print("response: ", response)
        print("response.text: ", response.text)

    def capture(self) : # 이미지 저장
        cv2.imwrite(r'C:\Users\user\Desktop\SendCamera\SendCamera\today_plant.png', self.frame)
        cv2.imwrite(r'C:\Users\user\Desktop\SendCamera\SendCamera\today_plant2.png', self.frame2)
            
    def cap_camera(self) :

        # IP 카메라 영상 호출
        #cap = cv2.VideoCapture('rtsp://admin:uoclab2023@192.168.0.36:10554/tcp/av0_0')
    
        cap = cv2.VideoCapture(0)
        cap2 = cv2.VideoCapture(1)
        count = 0
    
        while True:
            ret, self.frame = cap.read()
            ret2, self.frame2 = cap2.read()
            self.now = datetime.now()
        
            if ret:
        
                height, width = self.frame.shape[:2]

                height2, width2 = self.frame2.shape[:2]
        
                # 줄일 해상도
                new_height, new_width = height // 2, width // 2

                new_height2, new_width2 = height2 // 2, width2 // 2
        
                # 프레임의 해상도를 줄임
                self.frame = cv2.resize(self.frame, (new_width, new_height))

                self.frame2 = cv2.resize(self.frame2, (new_width2, new_height2))
    
                self.send_video(self.frame, self.frame2)
                time.sleep(0.05)
                
            else:
                print('카메라 오류')
                break
    
        cap.release()
        cv2.destroyAllWindows()

    def run(self) :
        
        th1 = Thread(target=self.cap_camera)

        schedule.every().day.at("13:30:00").do(self.capture) # 매일 정해진 시간에 캡쳐 => 나중에 지우고 get_today_state함수에 추가
        schedule.every().day.at("13:32:00").do(self.get_today_state) # 매일 정해진 시간에 상태 출력

        th1.start()
        while True :
            schedule.run_pending()
            time.sleep(1)

if __name__ == "__main__" :
    sio.connect('http://220.68.82.79:4000')     # Nas의 Flask-SocketIO 서버에 연결
    NewCamera = camera()
    NewCamera.run()
