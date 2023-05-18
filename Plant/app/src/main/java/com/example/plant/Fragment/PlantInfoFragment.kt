package com.example.plant.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.adapter.PlantInfoAdapter
import com.example.plant.adapter.PlantInfoGraphAdapter
import com.example.plant.databinding.FragmentPlantInfoBinding
import com.example.plant.model.PlantMoreInfo


class PlantInfoFragment : Fragment() {
    lateinit var plantInfoRecyclerView : RecyclerView
    lateinit var plantInfoAdapter: PlantInfoAdapter
    lateinit var plantInfoGraphRecyclerView : RecyclerView
    lateinit var plantInfoGraphAdapter: PlantInfoGraphAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantInfoBinding.inflate(inflater, container, false)

        val dataFruit = mutableListOf(
            PlantMoreInfo("품종", "화초"),
            PlantMoreInfo("별명", "쭉쭉이"),
            PlantMoreInfo("재배 시작일", "2023-05-10")
        )

        plantInfoAdapter = context?.let { PlantInfoAdapter(dataFruit) }!!
        binding.plantInfoMoreInfoRcv.adapter = plantInfoAdapter

        plantInfoRecyclerView = binding.plantInfoMoreInfoRcv
        plantInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoRecyclerView.adapter = PlantInfoAdapter(dataFruit)
        binding.plantInfoMoreInfoRcv.setHasFixedSize(true)

        val data = listOf(
            listOf(21.2f, 20.4f, 23.2f, 23.1f, 23.8f, 24.1f, 22.1f),
            listOf(33.7f, 34.1f, 32.1f, 35.0f, 32.1f, 33.1f, 34.0f)
        )

        plantInfoGraphAdapter = context?.let { PlantInfoGraphAdapter(data) }!!
        binding.plantInfoTempAndHumiRcv.adapter = plantInfoGraphAdapter

        plantInfoGraphRecyclerView = binding.plantInfoTempAndHumiRcv
        plantInfoGraphRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoGraphRecyclerView.adapter = PlantInfoGraphAdapter(data)
        binding.plantInfoTempAndHumiRcv.setHasFixedSize(true)

        return binding.root
    }
}