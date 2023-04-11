package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomeAdapter
import com.example.plant.databinding.FragmentHomeBinding
import com.example.plant.model.RecyclerInViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var recyclerView : RecyclerView
    lateinit var mainActivity: MainActivity
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeAdapter = context?.let { HomeAdapter(it) }!!
        binding.homeRcv.adapter = homeAdapter

        recyclerView = binding.homeRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = homeAdapter

        val itemList = mutableListOf(
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 1),
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 2))

        homeAdapter.datas = itemList
        homeAdapter.notifyDataSetChanged()

        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    private fun initRecycler(binding : FragmentHomeBinding) {
        homeAdapter = context?.let { HomeAdapter(it) }!!
        binding.homeRcv.adapter = homeAdapter

        val itemList = mutableListOf(
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 1),
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 2))

        homeAdapter.datas = itemList
        homeAdapter.notifyDataSetChanged()

    }
/*
    fun fetchJson(recyclerView : RecyclerView){
        //php 웹페이지 주소
        val url = URL("http://192.168.0.21:8080/test")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                Log.d("good1", "good1")

                //Gson으로 파싱
                val gson = GsonBuilder().create()
                Log.d("good2", body!!)
                val list = gson.fromJson(body, Array<Plant>::class.java)
                mainActivity.runOnUiThread {
                    recyclerView.adapter = HomeAdapter(requireContext(), list)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.w("error!!!", "Error writing document", e)
            }
        })
    }*/
}