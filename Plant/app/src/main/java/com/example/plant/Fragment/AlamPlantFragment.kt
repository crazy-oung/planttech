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
import com.example.plant.adapter.FruitAdapter
import com.example.plant.databinding.FragmentAlamPlantBinding
import com.example.plant.model.AlamContent

class AlamPlantFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    lateinit var alamPlantadapter: AlamPlantAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAlamPlantBinding.inflate(inflater, container, false)

        val data = mutableListOf(AlamContent("주인님 목이 말라요...", "멋쟁이[토마토]", "15분 전"),
            AlamContent("금일 15:30 ~ 17:30 점검 예정", "이용에 불편을 드려 죄송합니다.", "2시간 전"))
        alamPlantadapter = context?.let { AlamPlantAdapter(data) }!!
        binding.alamPlantRcv.adapter = alamPlantadapter

        recyclerView = binding.alamPlantRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = AlamPlantAdapter(data)
        binding.alamPlantRcv.setHasFixedSize(true)

        return binding.root
    }

}