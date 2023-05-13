package com.example.plant.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
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
            Plant(plantName = "토마토", plantScore = 100, startDate = "32일째", "Good"),
            Plant(plantName = "콩나물", plantScore = 80, startDate = "16일째", "Good"),
            Plant(plantName = "바나나", plantScore = 0, startDate = "4일째", "Soso"),
            Plant(plantName = "연꽃", plantScore = 0, startDate = "4일째", "Bad")
        )

        plantAdapter = context?.let { PlantAdapter(data) }!!
        binding.plantRcv.adapter = plantAdapter

        recyclerView = binding.plantRcv
        recyclerView.layoutManager = GridLayoutManager(context,3)
        recyclerView.adapter = PlantAdapter(data)

        plantAdapter.setItemClickListener(object :PlantAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}