package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.AlamPagerFragmentStateAdapter
import com.example.plant.adapter.HomePagerFragmentStateAdapter
import com.example.plant.databinding.FragmentAlamBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AlamFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlamBinding.inflate(inflater, container, false)


        tabLayout = binding.alamTabLayout
        viewPager = binding.alamPager

        val pagerAdapter = AlamPagerFragmentStateAdapter(requireActivity())


        pagerAdapter.addFragment(AlamPlantFragment())
        pagerAdapter.addFragment(AlamTradeFragment())

        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabList = listOf("식물", "거래")
        viewPager?.let {
            TabLayoutMediator(tabLayout, it){ tab, position ->
                tab.text = tabList[position]
            }.attach()
        }

        binding.alamToolbar.title = "알림"
        binding.alamToolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.alarm -> {
                    mainActivity.changeFragment(2)
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