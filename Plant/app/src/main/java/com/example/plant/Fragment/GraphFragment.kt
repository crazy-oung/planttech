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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.GraphAdapter
import com.example.plant.adapter.HomeAdapter
import com.example.plant.databinding.FragmentGraphBinding
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class GraphFragment : Fragment(R.layout.fragment_graph) {

    lateinit var mainActivity: MainActivity
    lateinit var recyclerView : RecyclerView
    lateinit var graphAdapter: GraphAdapter
    private lateinit var socket: Socket
    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentGraphBinding.inflate(inflater, container, false)

        val data = listOf(
            listOf(10f, 20f, 30f, 40f, 50f),
            listOf(50f, 40f, 30f, 20f, 10f),
            listOf(30f, 40f, 50f, 40f, 30f)
        )

        try {
            val options = IO.Options()
            socket = IO.socket("http://220.68.82.79:4000", options)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Log.e("접속실패!", e.toString())
        }
        socket.connect()
        socket.on("state") { args: Array<Any> ->
            mainActivity.runOnUiThread {
                Log.d("test", args[0].toString())
            }
        }

        recyclerView = binding.graphRcv
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = GraphAdapter(data)

        
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}