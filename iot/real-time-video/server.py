from flask import Flask
from flask_socketio import SocketIO, emit
from flask_cors import CORS
from threading import Thread

# Flask-SocketIO
app = Flask(__name__)
socketio = SocketIO(app, cors_allowed_origins="*")

# Flask-CORS
CORS(app)

init_data1 = None
init_data2 = None

# handle video1 event
@socketio.on('camera0')
def handle_video1(data):

    if data is not None :
        socketio.emit('video2', data)
    
@socketio.on('camera1')
def handle_video2(data):
    
    if data is not None :
        socketio.emit('test', data)

# Flask-SocketIO
if __name__ == '__main__':
    th1 = Thread(target=handle_video1(init_data1))
    th2 = Thread(target=handle_video2(init_data2))
    th1.start()
    th2.start()
    socketio.run(app, host='0.0.0.0', port=4000, debug=False)
