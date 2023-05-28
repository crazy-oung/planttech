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
import com.example.plant.model.GetUserBidListResponse
import com.example.plant.model.GetUserBidListResponseItem
import com.example.plant.model.ProfilePlantItemData
import com.example.plant.model.UserMeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoTradeFragment : Fragment() {

    private lateinit var data : MutableList<GetUserBidListResponseItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoTradeBinding.inflate(inflater, container, false)

        val service = ApiClient.getApiInterface()

        var sellCount = 0
        var buyCount = 0
        var sellNowCount = 0
        var sellLaterCount = 0
        var buyNowCount = 0
        var buyLaterCount = 0
        var sellMilage = 0
        var buyMilage = 0

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

        // 각 거래 몇개인지
        service.getUserBidList().enqueue( object : Callback<GetUserBidListResponse> {
            override fun onResponse(
                call: Call<GetUserBidListResponse>,
                response: Response<GetUserBidListResponse>
            ) {
                if (response.isSuccessful) {
                    val bidResponse = response.body()!!
                    Log.d("Gooood", bidResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    for ( i in bidResponse){
                        // 판매
                        if( i.productInstant == 0){
                            sellCount += 1
                            if(i.productType == 0){
                                sellNowCount += 1
                            }
                            else {
                                sellLaterCount += 1
                                sellMilage += i.productPrice!!
                            }
                        }
                        // 구매
                        else {
                            buyCount += 1
                            if(i.productType == 1){
                                buyNowCount += 1
                            }
                            else {
                                buyLaterCount += 1
                                buyMilage += i.productPrice!!
                            }
                        }
                        binding.infoSellAllTv.text = sellCount.toString()
                        binding.infoSellReadyTv.text = sellLaterCount.toString()
                        binding.infoSellEndTv.text = sellNowCount.toString()
                        binding.infoTradeSellNumTv.text = sellCount.toString()
                        binding.infoTradeSellPointTv.text = sellMilage.toString()

                        binding.infoBuyAllTv.text = buyCount.toString()
                        binding.infoBuyReadyTv.text = buyLaterCount.toString()
                        binding.infoBuyEndTv.text = buyNowCount.toString()
                        binding.infoTradeBuyNumTv.text = buyCount.toString()
                        binding.infoTradeBuyPointTv.text = buyMilage.toString()
                    }

                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<GetUserBidListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })


        // 더보기
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



        return binding.root
    }
}