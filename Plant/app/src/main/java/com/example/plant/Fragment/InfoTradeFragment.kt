package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.plant.R
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoTradeBinding
import com.example.plant.model.UserMeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoTradeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoTradeBinding.inflate(inflater, container, false)

        val service = ApiClient.getApiInterface()

        binding.infoBuyMoreBtn.setOnClickListener {
            service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse>{
                override fun onResponse(
                    call: Call<GetUserPlantResponse>,
                    response: Response<GetUserPlantResponse>
                ) {
                    if (response.isSuccessful){
                        val upResponse = response.body()!!

                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())


                    } else {
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                    }
                }

                override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }

            })
        }

        binding.infoSellMoreBtn.setOnClickListener {
            service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse>{
                override fun onResponse(
                    call: Call<GetUserPlantResponse>,
                    response: Response<GetUserPlantResponse>
                ) {
                    if (response.isSuccessful){
                        val upResponse = response.body()!!

                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())


                    } else {
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                    }
                }

                override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }

            })
        }

        binding.infoTradeMoreBtn.setOnClickListener {
            service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse>{
                override fun onResponse(
                    call: Call<GetUserPlantResponse>,
                    response: Response<GetUserPlantResponse>
                ) {
                    if (response.isSuccessful){
                        val upResponse = response.body()!!

                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())


                    } else {
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                    }
                }

                override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }

            })
        }
        service.userInfo().enqueue(object : Callback<UserMeResponse> {
            override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    Log.d("Gooood", response.body()!!.toString())

                    val infoResponse = response.body()!!

                    binding.infoMileageNumTv.text = infoResponse.userMileage.toString()
                    binding.infoMileageChargeTv.text = infoResponse.userMileage.toString()


                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }


            override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")

            }
        })
/*
        binding.infoBuyMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoPurchaseMoreFragment = InfoPurchaseMoreFragment()
            infoPurchaseMoreFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoPurchaseMoreFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.infoSellMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoSellMoreFragment = InfoSellMoreFragment()
            infoSellMoreFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoSellMoreFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.infoTradeMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoAllTradeListFragment = InfoAllTradeListFragment()
            infoAllTradeListFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoAllTradeListFragment)
                .addToBackStack(null)
                .commit()
        }
*/


        return binding.root
    }
}