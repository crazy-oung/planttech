package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.AlamPlantAdapter
import com.example.plant.adapter.FruitAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentAlamPlantBinding
import com.example.plant.model.AlamContent
import com.example.plant.model.PlantCategoryResponse
import com.example.plant.model.UserNotificationDeleteResponse
import com.example.plant.model.UserNotificationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlamPlantFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    lateinit var alamPlantadapter: AlamPlantAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlamPlantBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()

        service.userNotificationGet().enqueue(object : Callback<UserNotificationResponse> {
            override fun onResponse(call: Call<UserNotificationResponse>, response: Response<UserNotificationResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponse = response.body()!!
                    val data = mutableListOf<AlamContent>()
                    var num = 0

                    for(i in callResponse) {
                        //if (callResponse[num].userNotificationType == 3) {
                            data.add(
                                num, AlamContent(
                                    callResponse[num].userNotificationContent,
                                    callResponse[num].userNotificationCreatetime
                                )
                            )
                            num += 1
                        // }
                    }
                    alamPlantadapter = context?.let { AlamPlantAdapter(data) }!!
                    binding.alamPlantRcv.adapter = alamPlantadapter

                    recyclerView = binding.alamPlantRcv
                    recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = AlamPlantAdapter(data)
                    binding.alamPlantRcv.setHasFixedSize(true)

                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())


                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }



            override fun onFailure(call: Call<UserNotificationResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })
/*
        alamPlantadapter.setItemClickListener(object : AlamPlantAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                // 삭제할 알람의 data 변수 선언, 이후 api 명렁문안의 파라미터로 전달

                service.userNotificationDelete().enqueue(object : Callback<UserNotificationDeleteResponse> {
                    override fun onResponse(call: Call<UserNotificationDeleteResponse>, response: Response<UserNotificationDeleteResponse>) {
                        if (response.isSuccessful) {
                            // 정상적으로 통신이 성공된 경우
                            val callResponse = response.body()!!
                            //

                            Log.d("Gooood", callResponse.toString())
                            Log.d("Gooood", response.headers().toString())


                        } else {
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                            Log.d("Baaaad", response.toString())
                        }
                    }



                    override fun onFailure(call: Call<UserNotificationDeleteResponse>, t: Throwable) {
                        Log.d("Real Baaaad", "onResponse 대실패")
                    }
                })
            }
        })*/

        return binding.root
    }

}