package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.adapter.RankAdapter
import com.example.plant.databinding.FragmentRankBinding
import com.example.plant.model.Plant

class RankFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var mainActivity: MainActivity
    lateinit var rankAdapter: RankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRankBinding.inflate(inflater, container, false)

        val data = mutableListOf(
            Plant(plantName = "토마토", plantScore = 100, startDate = "32일째", "Good"),
            Plant(plantName = "콩나물", plantScore = 80, startDate = "16일째", "Good"),
            Plant(plantName = "바나나", plantScore = 0, startDate = "4일째", "Soso"),
            Plant(plantName = "연꽃", plantScore = 0, startDate = "4일째", "Bad")
        )

        rankAdapter = context?.let { RankAdapter(data) }!!
        binding.homeMainRcv.adapter = rankAdapter

        recyclerView = binding.homeMainRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = rankAdapter

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

}