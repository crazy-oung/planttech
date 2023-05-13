package com.example.plant.Fragment

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.CameraAdapter
import com.example.plant.databinding.FragmentCameraBinding
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class CameraFragment : Fragment(R.layout.fragment_camera) {

    lateinit var mainActivity: MainActivity
    lateinit var cameraAdapter: CameraAdapter
    private lateinit var socket: Socket
    private lateinit var imageView: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentCameraBinding.inflate(inflater, container, false)

        imageView = binding.CameraView

        try {
            val options = IO.Options()
            socket = IO.socket("http://220.68.82.79:4000", options)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Log.e("접속실패!", e.toString())
        }
        socket.connect()
        socket.on("video2") { args: Array<Any> ->
            val arg = args[0] as ByteArray
            mainActivity.runOnUiThread {
                val bitmap = BitmapFactory.decodeByteArray(arg, 0, arg.size)
                imageView.setImageBitmap(bitmap)
            }
        }


        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}
