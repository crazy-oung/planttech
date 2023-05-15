package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomePagerFragmentStateAdapter
import com.example.plant.adapter.InfoAdapter
import com.example.plant.adapter.InfoPagerFragmentStateAdapter
import com.example.plant.databinding.FragmentInfoBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class InfoFragment : Fragment(R.layout.fragment_info) {

    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentInfoBinding.inflate(inflater, container, false)

        val pagerAdapter = InfoPagerFragmentStateAdapter(requireActivity())

        viewPager = binding.infoPager
        tabLayout = binding.infoTabLayout

        pagerAdapter.addFragment(InfoTradeFragment())
        pagerAdapter.addFragment(InfoProfileFragment())

        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabList = listOf<String>("내 거래", "내 프로필")
        viewPager?.let {
            TabLayoutMediator(tabLayout, it){ tab, position ->
                tab.text = tabList[position]
            }.attach()
        }

        return binding.root



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}
