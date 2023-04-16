package com.example.getcamera

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    private lateinit var socket: Socket
    private lateinit var imageView: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val options = IO.Options()
            socket = IO.socket("http://220.68.82.79:4000", options)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        imageView = findViewById(R.id.ImageView)
        socket.connect()

        socket.on("video2") { args ->
            val arg = args[0] as ByteArray
            Log.d("데이터받았습니다.", arg.toString())

            runOnUiThread {
                val bitmap = BitmapFactory.decodeByteArray(arg, 0, arg.size)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
    }
}