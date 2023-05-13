package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.FruitAdapter
import com.example.plant.adapter.OrnaAdapter
import com.example.plant.adapter.VegiAdapter
import com.example.plant.databinding.FragmentFruitBinding
import com.example.plant.databinding.FragmentVegitableBinding
import com.example.plant.model.Plant

class VegitableFragment : Fragment() {
    lateinit var vegiRecyclerView : RecyclerView
    lateinit var vegiAdapter: VegiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVegitableBinding.inflate(inflater, container, false)
        val dataVegi = mutableListOf(
            Plant(plantName = "쭉쭉이", plantScore = 100, startDate = "32일째", "콩나물","Good", true),
            Plant(plantName = "보쌈", plantScore = 80, startDate = "16일째", "적상추", "Good", true)
        )

        vegiAdapter = context?.let { VegiAdapter(dataVegi) }!!
        binding.vegiRcv.adapter = vegiAdapter

        vegiRecyclerView = binding.vegiRcv
        vegiRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        vegiRecyclerView.adapter = VegiAdapter(dataVegi)
        binding.vegiRcv.setHasFixedSize(true)

        return binding.root
    }
}