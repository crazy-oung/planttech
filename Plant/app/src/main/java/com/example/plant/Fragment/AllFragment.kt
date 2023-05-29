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
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentAllBinding
import com.example.plant.model.GetUserPlantSensorListRequest
import com.example.plant.model.HomePlantItemData
import com.example.plant.model.Plant
import com.example.plant.model.PlantImageGetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllFragment : Fragment() {
    lateinit var fruitRecyclerView : RecyclerView
    lateinit var fruitAdapter: FruitAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAllBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()

        var count = 0
        val state = mutableListOf<String>("Very Good", "Bad", "Soso")
        val plantNickname = listOf<String>("뾰족이", "공기청정기", "나난나" )


        service.getUserPlant().enqueue(object : Callback<GetUserPlantResponse> {
            override fun onResponse(call: Call<GetUserPlantResponse>, response: Response<GetUserPlantResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callPlantResponse = response.body()!!
                    Log.d("Gooood", callPlantResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    val plantData = mutableListOf<HomePlantItemData>()
                    for ( i in callPlantResponse) {
                        service.getPlantSensorList(i.warehousePlantNo!!, 10)
                            .enqueue(object : Callback<GetUserPlantSensorListRequest> {
                                override fun onResponse(
                                    call: Call<GetUserPlantSensorListRequest>,
                                    response: Response<GetUserPlantSensorListRequest>
                                ) {
                                    if (response.isSuccessful) {
                                        val callSensorResponse = response.body()!!
                                        Log.d("Gooood", callSensorResponse.toString())
                                        Log.d("Gooood", response.headers().toString())

                                        //
                                        service.plantImage(0,10, null, i.warehousePlantNo, null).enqueue( object : Callback<PlantImageGetResponse> {
                                            override fun onResponse(
                                                call: Call<PlantImageGetResponse>,
                                                response: Response<PlantImageGetResponse>
                                            ) {
                                                if(response.isSuccessful){
                                                    val callPhotoResponse = response.body()!!
                                                    Log.d("Gooood", callPhotoResponse.toString())
                                                    Log.d("Gooood", response.headers().toString())

                                                    if( i.warehousePlantNo == 2){
                                            plantData.add( HomePlantItemData(
                                                plantNickname[count],
                                                i.plantKoreanName!!,
                                                i.plantCategory!!,
                                                callPhotoResponse[0].plantColorGrade,
                                                0.0,
                                                0.0,
                                                i.plantNo!!,
                                                i.warehousePlantNo
                                            )
                                            )
                                        } else {
                                            plantData.add(
                                                HomePlantItemData(
                                                    plantNickname[count],
                                                    i.plantKoreanName!!,
                                                    i.plantCategory!!,
                                                    callPhotoResponse[0].plantColorGrade,
                                                    callSensorResponse[0].temp!!,
                                                    callSensorResponse[0].humi!!,
                                                    i.plantNo!!,
                                                    i.warehousePlantNo
                                                )
                                            )
                                        }
                                        fruitAdapter = context?.let { FruitAdapter(plantData) }!!
                                        binding.homeFruitRcv.adapter = fruitAdapter

                                        fruitRecyclerView = binding.homeFruitRcv
                                        fruitRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                                        fruitRecyclerView.adapter = FruitAdapter(plantData)
                                        binding.homeFruitRcv.setHasFixedSize(true)

                                        count += 1
                                                } else {
                                                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                                                    Log.d("Baaaad", response.toString())
                                                }
                                            }

                                            override fun onFailure(call: Call<PlantImageGetResponse>, t: Throwable) {
                                                Log.d("Real Baaaad", "onResponse 대실패")
                                            }

                                        })
                                    } else {
                                        Log.d("BaaaadSensor",
                                            NetworkUtil.getErrorResponse(response.errorBody()!!)
                                                .toString()
                                        )
                                        Log.d("BaaaadSensor", response.toString())


                                    }
                                }

                                override fun onFailure(
                                    call: Call<GetUserPlantSensorListRequest>,
                                    t: Throwable
                                ) {
                                    Log.d("Real Baaaad", "onResponse 대실패")
                                }

                            })
                    }
                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("BaaaadUserPlant", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("BaaaadUserPlant", response.toString())
                }
            }


            override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })




        return binding.root
    }

}