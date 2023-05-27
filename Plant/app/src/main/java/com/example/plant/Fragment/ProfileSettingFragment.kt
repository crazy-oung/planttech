package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.adapter.ProfileSettingAdapter

import com.example.plant.databinding.FragmentProfileSettingBinding
import com.example.plant.model.ProfileSettingContent
import com.google.android.material.tabs.TabLayout

class ProfileSettingFragment : Fragment() {

    private lateinit var plantSettingRecyclerView : RecyclerView
    private lateinit var tradeSettingRecyclerView : RecyclerView
    lateinit var profileSettingadapter: ProfileSettingAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileSettingBinding.inflate(inflater, container, false)


        binding.profileToolbarSetting.title = "설정"


        val dataSetPlant = mutableListOf(
            ProfileSettingContent("로그인 정보", ),
            ProfileSettingContent("알림", ),
            ProfileSettingContent("로그아웃", )
        )


        profileSettingadapter = context?.let { ProfileSettingAdapter(dataSetPlant) }!!
        binding.profilePlantSettingRcv.adapter = profileSettingadapter

        plantSettingRecyclerView = binding.profilePlantSettingRcv
        plantSettingRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        plantSettingRecyclerView.adapter = ProfileSettingAdapter(dataSetPlant)
        binding.profilePlantSettingRcv.setHasFixedSize(true)



        return binding.root
    }
}