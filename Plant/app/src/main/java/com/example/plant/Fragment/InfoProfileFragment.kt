package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoProfileBinding
import com.example.plant.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoProfileFragment : Fragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var infoProfileAdapter: InfoProfileAdapter
    private lateinit var data : MutableList<ProfilePlantItemData>
    private lateinit var plantCategory : PlantCategoryResponse
    private lateinit var plantListRequest: PlantListRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoProfileBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        var state = "Very Good"
        binding.infoPlantMoreBtn.setOnClickListener {

        }




        // 유저 플랜트 정보 목록
        service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse>{
            override fun onResponse(
                call: Call<GetUserPlantResponse>,
                response: Response<GetUserPlantResponse>
            ) {
                if (response.isSuccessful){
                    val plantResponse = response.body()!!

                    binding.infoProfilePlantAllTv.text = plantResponse.size.toString()
                    Log.d("Gooood", plantResponse.toString())
                    Log.d("Gooood", response.headers().toString())


                    for ( i in plantResponse){
                         service.getPlantSensorList(
                            i.warehousePlantNo!!,
                            30
                        ).enqueue( object : Callback<GetUserPlantSensorListRequest> {
                            override fun onResponse(
                                call: Call<GetUserPlantSensorListRequest>,
                                response: Response<GetUserPlantSensorListRequest>
                            ) {
                                if(response.isSuccessful){
                                    val sensorResponse = response.body()
                                    Log.d("Gooood", sensorResponse.toString())
                                    Log.d("Gooood", response.headers().toString())

                                    data.add(
                                        ProfilePlantItemData(
                                            i.plantKoreanName!!,
                                            i.plantCategory!!,
                                            state,
                                            sensorResponse!![0].temp,
                                            sensorResponse[0].humi
                                        )
                                    )

                                    binding.infoProfilePlantAllTv.text = data.size.toString()
                                    binding.infoProfilePlantIngTv.text = data.size.toString()
                                    infoProfileAdapter = context?.let { InfoProfileAdapter(data) }!!
                                    binding.infoProfileMysellRcv.adapter = infoProfileAdapter

                                    recyclerView = binding.infoProfileMysellRcv
                                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                                    recyclerView.adapter = InfoProfileAdapter(data)
                                    binding.infoProfileMysellRcv.setHasFixedSize(true)
                                } else {
                                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                                    Log.d("Baaaad", response.toString())
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
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })



        service.plantCategory().enqueue(object : Callback<PlantCategoryResponse> {
            override fun onResponse(call: Call<PlantCategoryResponse>, response: Response<PlantCategoryResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val plantResponse = response.body()!!

                    Log.d("Gooood", plantResponse.toString())
                    Log.d("Gooood", response.headers().toString())


                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }



            override fun onFailure(call: Call<PlantCategoryResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })



        data = mutableListOf(
            // 채워야함
        )



        binding.infoPlantMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoPlantMoreFragment = InfoPlantMoreFragment()
            infoPlantMoreFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoPlantMoreFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}