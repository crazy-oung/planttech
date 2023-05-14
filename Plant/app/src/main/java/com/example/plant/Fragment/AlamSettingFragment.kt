package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.AlamPlantAdapter
import com.example.plant.adapter.AlamPlantSettingAdapter
import com.example.plant.adapter.AlamTradeSettingAdapter
import com.example.plant.databinding.FragmentAlamPlantBinding
import com.example.plant.databinding.FragmentAlamSettingBinding
import com.example.plant.model.AlamContent
import com.example.plant.model.AlamSettingContent
import com.google.android.material.tabs.TabLayout

class AlamSettingFragment : Fragment() {

    private lateinit var plantSettingRecyclerView : RecyclerView
    private lateinit var tradeSettingRecyclerView : RecyclerView
    lateinit var plantSettingadapter: AlamPlantSettingAdapter
    lateinit var tradeSettingadapter: AlamTradeSettingAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlamSettingBinding.inflate(inflater, container, false)


        binding.alamToolbarSetting.title = "알림"


        val dataSetPlant = mutableListOf(
            AlamSettingContent("센서 작동 알림", "가습기, 워터 펌프 등의 센서 작동에 대한 알림"),
            AlamSettingContent("식물 상태 알림", "식물의 상태가 변화되는 것에 대한 알림")
        )

        val dataSetTrade = mutableListOf(
            AlamSettingContent("구매 거래 알림", "거래 및 구매에 대한 알림"),
            AlamSettingContent("판매 거래 알림", "거래 및 판매에 대한 알림"),
            AlamSettingContent("관심 식물 등록 알림", "관심 목록에 체크해둔 식물에 대한 등록 알림")
        )
        plantSettingadapter = context?.let { AlamPlantSettingAdapter(dataSetPlant) }!!
        binding.alamPlantSettingRcv.adapter = plantSettingadapter

        plantSettingRecyclerView = binding.alamPlantSettingRcv
        plantSettingRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        plantSettingRecyclerView.adapter = AlamPlantSettingAdapter(dataSetPlant)
        binding.alamPlantSettingRcv.setHasFixedSize(true)


        tradeSettingadapter = context?.let { AlamTradeSettingAdapter(dataSetTrade) }!!
        binding.alamTradeSettingRcv.adapter = plantSettingadapter

        tradeSettingRecyclerView = binding.alamTradeSettingRcv
        tradeSettingRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        tradeSettingRecyclerView.adapter = AlamTradeSettingAdapter(dataSetTrade)
        binding.alamTradeSettingRcv.setHasFixedSize(true)

        return binding.root
    }
}