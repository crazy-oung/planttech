package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.adapter.ProfileBuyListMoreInfoAdapter
import com.example.plant.adapter.ProfilePlantListMoreInfoAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoPurchaseListOneBinding
import com.example.plant.model.GetUserBidListResponse
import com.example.plant.model.GetUserBidListResponseItem
import com.example.plant.model.GetUserPlantSensorListRequest
import com.example.plant.model.ProfilePlantItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPurchaseListOneFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var recyclerView : RecyclerView
    lateinit var profileBuyAdapter: ProfileBuyListMoreInfoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoPurchaseListOneBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()

        binding.infoPurchaseSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sortPurchaseItemList, R.layout.spinner_item)
        binding.infoPurchaseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?,view: View?,
                                        position: Int, id: Long) {

            }
        }

        service.getUserBidList().enqueue(object  : Callback<GetUserBidListResponse> {
            override fun onResponse(
                call: Call<GetUserBidListResponse>,
                response: Response<GetUserBidListResponse>
            ) {
                if (response.isSuccessful){
                    val bidResponse = response.body()!!

                    Log.d("Gooood", bidResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    var data = mutableListOf<GetUserBidListResponseItem>()
                    for (i in bidResponse) {
                        if( i.productInstant == 1) {
                            data.add(
                                GetUserBidListResponseItem(
                                    null, null, null, null, null, null,
                                    null,
                                    i.productName,
                                    i.productPrice,
                                    null,
                                    i.productScoreNo,null, // Two, Three는 null 아님
                                    null,
                                    0,
                                    null,
                                    null,
                                    null
                                )
                            )
                        }
                    }


                    profileBuyAdapter = context?.let { ProfileBuyListMoreInfoAdapter(data) }!!
                    binding.profileBuyMoreinfoOneRcv.adapter = profileBuyAdapter

                    recyclerView = binding.profileBuyMoreinfoOneRcv
                    recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = profileBuyAdapter
                    binding.profileBuyMoreinfoOneRcv.setHasFixedSize(true)

                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<GetUserBidListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

}