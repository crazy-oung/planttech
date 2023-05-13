package com.example.plant.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.HomeAdapter
import com.example.plant.adapter.RealInfoAdapter
import com.example.plant.databinding.FragmentRealinfoBinding
import com.example.plant.model.*

class InfoFragment : Fragment(R.layout.fragment_realinfo) {
    lateinit var recyclerView : RecyclerView
    lateinit var realInfoAdapter: RealInfoAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentRealinfoBinding.inflate(inflater, container, false)

        realInfoAdapter = context?.let { RealInfoAdapter(it) }!!
        binding.infoRcv.adapter = realInfoAdapter

        recyclerView = binding.infoRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = realInfoAdapter

        return binding.root
        /*
        val retrofit = Retrofit.Builder().baseUrl("http://dayounghan.com/") //http://192.168.0.21:8080/ //http://dayounghan.com/
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(RetrofitService::class.java);

        service.getPlant()?.enqueue(object : Callback<List<PlantInfo>>{
            override fun onResponse(call: Call<List<PlantInfo>>, response: Response<List<PlantInfo>>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성고된 경우
                    binding.info.text = response.body().toString()
                    Log.d("Gooood", "onResponse 성공: ");
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", "onResponse 실패")
                }
            }
            override fun onFailure(call: Call<List<PlantInfo>>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("Very Baaaad", "onFailure 에러: " + t.message.toString());
            }
        })


        binding.postButton.setOnClickListener{

            service.LEDRequest(0,binding.infoEdit2.text.toString().toInt()).enqueue(object : Callback<LedResponse>{
                override fun onResponse(call: Call<LedResponse>, response: Response<LedResponse>) {
                    if(response.isSuccessful){
                        // 정상적으로 통신이 성고된 경우
                        binding.response.text = response.body().toString()
                        Log.d("Gooood", "onResponse 성공: ")
                    }else{
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Log.d("Baaaad", "onResponse 실패" + response.code().toString())
                    }
                }

                override fun onFailure(call: Call<LedResponse>, t: Throwable) {
                    // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                    Log.d("Very Baaaad", "onFailure 에러: " + t.message.toString());
                }

            })
        }*/

    }
}
