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
# from remove_bg import *
# from rem_bg import *

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
    state = None   # 식물 상태
    cam_list = [] # 연결된 카메라 리스트
    cam_num = 0 # 카메라 개수
    img_path = r'C:\Users\user\Desktop\SendCamera\SendCamera'   # 이미지 기본 저장 경로
    now = datetime.now()   # 현재 시간

    def connect_camera(self) : # 연결된 카메라 검사 함수
        cam_index = 0   # 0번은 노트북 카메라 이므로 1번부터 시작
        while True : # 1번(웹캠 호출 시작)
            cam = cv2.VideoCapture(cam_index)
            if not cam.isOpened() :
                break
            self.cam_list.append(cam) # 카메라 추가
            print("%d번 카메라 추가" %(self.cam_num+1))
            self.cam_num += 1
            cam_index += 1

    # Flask-SocketIO 서버로 영상을 전송하는 함수
    def send_video(self, frame, cam_index):
        if frame is not None :
            # 영상 프레임을 base64로 인코딩하여 문자열로 변환
            _, buffer = cv2.imencode('.jpg', frame)
            data = base64.b64encode(buffer).decode('utf-8')

            # 영상 데이터를 Flask-SocketIO 서버로 전송
            event_name = "camera" + str(cam_index)
            sio.emit(event_name, data)

    def get_today_state(self) : # 하루에 한 번씩 정해진 시간마다 상태 얻기
        for i in range(self.cam_num) :
            _, frame = self.cam_list[i].read()
            file_name = self.img_path + '\\test' + str(i) + '.png'
            cv2.imwrite(file_name, frame)

            self.state, avg_color = get_state(file_name)
            print(self.state)
            print(avg_color)
            self.send_api(i, self.state, frame)
        

    def send_api(self, cam_index, state, frame) : # api로 식물 상태 데이터 보내는 함수

        _, buffer = cv2.imencode('.jpg', frame)
        color_img = base64.b64encode(buffer).decode('utf-8')

        url = "http://dayounghan.com/ai/plant/color-analysis"
#        url = "http://192.168.0.21:8080/ai/plant/color-analysis"

        headers = {"Content-Type": "application/json"}

        temp = {
            "plantWarehouseNo": cam_index,
            "plantColorGrade": state,
            "plantColorPic": color_img
        }

        data = json.dumps(temp)
        response = requests.post(url, headers=headers, data=data)

        print("response: ", response)
        print("response.text: ", response.text)
            
    def cap_camera(self) :   # 카메라 영상 호출 함수

        self.connect_camera()
    
        while True :
            for cam_index in range(self.cam_num) :
                ret, frame = self.cam_list[cam_index].read()
                if ret :
                    height, width = frame.shape[:2]
                
                    # 줄일 해상도
                    new_height, new_width = height // 2, width // 2
                
                    # 프레임의 해상도를 줄임
                    frame = cv2.resize(frame, (new_width, new_height))
        
                    self.send_video(frame, cam_index)
        
                    time.sleep(0.05)


    def run(self) :
        
        th1 = Thread(target=self.cap_camera)

        schedule.every().day.at("18:42:30").do(self.get_today_state) # 매일 정해진 시간에 상태 출력

        th1.start()
        while True :
            schedule.run_pending()
            time.sleep(0.05)

if __name__ == "__main__" :
    sio.connect('http://220.68.82.79:4000')     # Nas의 Flask-SocketIO 서버에 연결
    NewCamera = camera()
    NewCamera.run()

    for i in range(len(NewCamera.cam_list)) :   # 등록된 카메라 해제
        NNewCamera.cam_list[i].release

    sio.disconnect('http://220.68.82.79:4000')
    cv2.destroyAllWindows()
