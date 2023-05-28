package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.adapter.FruitAdapter
import com.example.plant.adapter.OrnaAdapter
import com.example.plant.adapter.VegiAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentAllBinding
import com.example.plant.model.Plant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllFragment : Fragment() {
    lateinit var fruitRecyclerView : RecyclerView
    lateinit var fruitAdapter: FruitAdapter
    lateinit var plantData : MutableList<GetUserPlantResponseItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAllBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()

        service.getUserPlant().enqueue(object : Callback<GetUserPlantResponse> {
            override fun onResponse(call: Call<GetUserPlantResponse>, response: Response<GetUserPlantResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponse = response.body()!!
                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())
/*
                    for(i in callResponse){
                        plantData.add(
                            GetUserPlantResponseItem(
                                // api 연결 이후 확인 필요

                            )
                        )
                    }
*/
                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }


            override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })

        val dataFruit = mutableListOf(
            Plant(plantName = "멋쟁이", plantScore = 100, startDate = "32일째", "토마토","Good", true),
            Plant(plantName = "새콤달콤", plantScore = 80, startDate = "16일째", "낑깡", "Good", true),
            Plant(plantName = "길쭉이", plantScore = 0, startDate = "4일째", "바나나","Soso", false),
            Plant(plantName = "작은멋쟁이", plantScore = 0, startDate = "4일째", "방울토마토","Bad", false)
        )



        fruitAdapter = context?.let { FruitAdapter(dataFruit) }!!
        binding.homeFruitRcv.adapter = fruitAdapter

        fruitRecyclerView = binding.homeFruitRcv
        fruitRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fruitRecyclerView.adapter = FruitAdapter(dataFruit)
        binding.homeFruitRcv.setHasFixedSize(true)


        return binding.root
    }

}