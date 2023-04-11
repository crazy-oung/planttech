package com.example.plant.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.HomeAdapter
import com.example.plant.adapter.PlantAdapter
import com.example.plant.databinding.FragmentPlantBinding
import com.example.plant.model.Plant
import java.util.*

class PlantFragment : Fragment(R.layout.fragment_plant) {

    lateinit var recyclerView : RecyclerView
    lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentPlantBinding.inflate(inflater, container, false)

        val data = mutableListOf(
            Plant(plantName = "토마토", plantScore = 100, startDate = Date(System.currentTimeMillis())),
            Plant(plantName = "콩나물", plantScore = 80, startDate = Date(System.currentTimeMillis())),
            Plant(plantName = "바나나", plantScore = 0, startDate = Date(System.currentTimeMillis()))
        )

        plantAdapter = context?.let { PlantAdapter(data) }!!
        binding.plantRcv.adapter = plantAdapter

        val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.plantRcv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PlantAdapter(data)


        plantAdapter.setItemClickListener(object :PlantAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
            }
        })

        return binding.root
    }



}