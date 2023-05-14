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
import com.example.plant.adapter.AlamTradeAdapter
import com.example.plant.databinding.FragmentAlamPlantBinding
import com.example.plant.databinding.FragmentAlamTradeBinding
import com.example.plant.model.AlamContent

class AlamTradeFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    lateinit var alamTradeadapter: AlamTradeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlamTradeBinding.inflate(inflater, container, false)

        val data = mutableListOf(
            AlamContent("판매 완료", "3,300 마일리지가 입금되었습니다.", "1분 전"),
            AlamContent("관심 식물 등록", "관심 목록에 있는 식물이 새롭게 등록되었습니다.", "5시간 전")
        )
        alamTradeadapter = context?.let { AlamTradeAdapter(data) }!!
        binding.alamTradeRcv.adapter = alamTradeadapter

        recyclerView = binding.alamTradeRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = AlamTradeAdapter(data)
        binding.alamTradeRcv.setHasFixedSize(true)

        return binding.root
        return inflater.inflate(R.layout.fragment_alam_trade, container, false)
    }

}