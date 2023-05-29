package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomePagerFragmentStateAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentHomeBinding
import com.example.plant.model.LoginCall
import com.example.plant.model.User
import com.example.plant.model.UserMeResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()

        binding.homeToolbar.title = "내 식물"


        service.userInfo().enqueue(object : Callback<UserMeResponse> {
            override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    Log.d("Gooood", response.body()!!.toString())
                    val userResponse = response.body()


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

        viewPager = binding.pager
        tabLayout = binding.tabLayout

        val pagerAdapter = HomePagerFragmentStateAdapter(requireActivity())


        pagerAdapter.addFragment(AllFragment())

        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabList = listOf<String>("전체")
        viewPager?.let {
            TabLayoutMediator(tabLayout, it){ tab, position ->
                tab.text = tabList[position]
            }.attach()
        }
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


        binding.homeToolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.alarm -> {
                    mainActivity.changeFragment(1)
                    true
                }

                R.id.add -> {
                    mainActivity.changeFragment(3)
                    true
                }

                else -> false
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}