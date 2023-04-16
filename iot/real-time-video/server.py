from datetime import datetime
from flask import Flask, Response, request, send_file
from flask_socketio import SocketIO, emit, send
from flask_cors import CORS
import base64
import cv2
import numpy as np
import eventlet

# Flask-SocketIO
app = Flask(__name__)
socketio = SocketIO(app, cors_allowed_origins="*")

# Flask-CORS
CORS(app)

# handle video1 event
@socketio.on('video1')
def handle_video1(data):
	
    data = base64.b64decode(data.encode('utf-8'))
    socketio.emit('video2', data)

# Flask-SocketIO
if __name__ == '__main__':
    socketio.run(app, host='0.0.0.0', port=4000, debug=False)