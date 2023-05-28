package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.InfoMorePlantPagerFragmentStateAdapter
import com.example.plant.adapter.InfoMorePurchasePagerFragmentStateAdapter
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoPlantMoreBinding
import com.example.plant.databinding.FragmentInfoPurchaseMoreBinding
import com.example.plant.model.GetUserPlantSensorListRequest
import com.example.plant.model.ProfilePlantItemData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPlantMoreFragment : Fragment(R.layout.fragment_home) {

    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentInfoPlantMoreBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        viewPager = binding.pager
        tabLayout = binding.infoMorePlantTabLayout

        val pagerAdapter = InfoMorePlantPagerFragmentStateAdapter(requireActivity())


        pagerAdapter.addFragment(ProfileMyPlantOneFragment())
        pagerAdapter.addFragment(ProfileMyPlantTwoFragment())
        pagerAdapter.addFragment(ProfileMyPlantThreeFragment())

        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })



        service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse> {
            override fun onResponse(
                call: Call<GetUserPlantResponse>,
                response: Response<GetUserPlantResponse>
            ) {
                if (response.isSuccessful){
                    val plantResponse = response.body()!!

                    Log.d("Gooood", plantResponse.toString())
                    Log.d("Gooood", response.headers().toString())


                    val allText = plantResponse.size.toString()
                    val ingText = plantResponse.size.toString()

                    val tabList = listOf("$allText"+"\n전체", "$ingText"+"\n재배중", "0"+"\n재배 완료")

                    viewPager?.let {
                        TabLayoutMediator(tabLayout, it){ tab, position ->
                            tab.text = tabList[position]
                        }.attach()
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

        binding.infoPlantToolbar.title = "내 식물"


        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}