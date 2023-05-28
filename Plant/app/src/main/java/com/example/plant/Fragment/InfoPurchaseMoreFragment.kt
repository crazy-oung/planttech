package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.InfoMorePurchasePagerFragmentStateAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoPurchaseMoreBinding
import com.example.plant.model.GetUserBidListResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPurchaseMoreFragment : Fragment(R.layout.fragment_home) {

    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentInfoPurchaseMoreBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()


        var buyCount = 0
        var buyNowCount = 0
        var buyLaterCount = 0
        var buyMilage = 0

        viewPager = binding.pager
        tabLayout = binding.infoMoreTabLayout

        val pagerAdapter = InfoMorePurchasePagerFragmentStateAdapter(requireActivity())


        pagerAdapter.addFragment(InfoPurchaseListOneFragment())
        pagerAdapter.addFragment(InfoPurchaseListTwoFragment())
        pagerAdapter.addFragment(InfoPurchaseListThreeFragment())

        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        service.getUserBidList().enqueue(object  : Callback<GetUserBidListResponse> {
            override fun onResponse(
                call: Call<GetUserBidListResponse>,
                response: Response<GetUserBidListResponse>
            ) {
                if (response.isSuccessful){
                    val bidResponse = response.body()!!

                    Log.d("Gooood", bidResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    for ( i in bidResponse){
                        if ( i.productInstant == 1) {
                            buyCount += 1
                            if (i.productType == 1) {
                                buyNowCount += 1
                            } else {
                                buyLaterCount += 1
                                buyMilage += i.productPrice!!
                            }
                        }

                    val allText = buyCount.toString()
                    val ingText = buyLaterCount.toString()
                    val endText = buyNowCount.toString()

                    val tabList = listOf("$allText"+"\n전체", "$ingText"+"\n입찰 대기", "$endText"+"\n종료")

                        viewPager?.let {
                            TabLayoutMediator(tabLayout, it){ tab, position ->
                                tab.text = tabList[position]
                            }.attach()
                         }
                    }
                } else {
                        Log.d(
                            "Baaaad",
                            NetworkUtil.getErrorResponse(response.errorBody()!!).toString()
                        )
                        Log.d("Baaaad", response.toString())
                    }

            }

            override fun onFailure(call: Call<GetUserBidListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })
        /*
        val data = mutableListOf(
            Plant(plantName = "토마토", plantScore = 100, startDate = "32일째", "Good"),
            Plant(plantName = "콩나물", plantScore = 80, startDate = "16일째", "Good"),
            Plant(plantName = "바나나", plantScore = 0, startDate = "4일째", "Soso"),
            Plant(plantName = "연꽃", plantScore = 0, startDate = "4일째", "Bad")
        )

        homeAdapter = context?.let { HomeAdapter(data) }!!
        binding.homeMainRcv.adapter = homeAdapter

        recyclerView = binding.homeMainRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = homeAdapter
        */

        binding.infoPurchaseToolbar.title = "구매 내역"


        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}