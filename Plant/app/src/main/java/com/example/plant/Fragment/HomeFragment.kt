package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomePagerFragmentStateAdapter
import com.example.plant.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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

        viewPager = binding.pager
        tabLayout = binding.tabLayout

        val pagerAdapter = HomePagerFragmentStateAdapter(requireActivity())

        pagerAdapter.addFragment(AllFragment())
        pagerAdapter.addFragment(StarFragment())
        pagerAdapter.addFragment(FruitFragment())
        pagerAdapter.addFragment(VegitableFragment())
        pagerAdapter.addFragment(OrnamentalFragment())

        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabList = listOf<String>("전체", "관심", "과일", "채소", "관상식물")
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
        return binding.root

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}