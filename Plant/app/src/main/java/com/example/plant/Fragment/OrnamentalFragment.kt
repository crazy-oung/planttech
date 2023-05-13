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
import com.example.plant.databinding.FragmentOrnamentalBinding
import com.example.plant.databinding.FragmentVegitableBinding
import com.example.plant.model.Plant

class OrnamentalFragment : Fragment() {
    lateinit var ornaRecyclerView : RecyclerView
    lateinit var ornaAdapter: OrnaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOrnamentalBinding.inflate(inflater, container, false)
        val dataOrna = mutableListOf(
            Plant(plantName = "가시가시", plantScore = 100, startDate = "32일째", "장미","Good", false),
            Plant(plantName = "할미", plantScore = 80, startDate = "16일째", "할미꽃", "Good", false)
        )
        ornaAdapter = context?.let { OrnaAdapter(dataOrna) }!!
        binding.ornaRcv.adapter = ornaAdapter

        ornaRecyclerView = binding.ornaRcv
        ornaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        ornaRecyclerView.adapter = VegiAdapter(dataOrna)
        binding.ornaRcv.setHasFixedSize(true)

        return binding.root
    }
}