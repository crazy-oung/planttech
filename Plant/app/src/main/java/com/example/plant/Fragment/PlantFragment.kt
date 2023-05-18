package com.example.plant.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.PlantAdapter
import com.example.plant.databinding.FragmentPlantBinding
import com.example.plant.model.Plant


class PlantFragment : Fragment(R.layout.fragment_plant) {

    lateinit var recyclerView : RecyclerView
    lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentPlantBinding.inflate(inflater, container, false)


        val data = mutableListOf(
            Plant(plantName = "멋쟁이", plantScore = 100, startDate = "32일째", "토마토","Good", true),
            Plant(plantName = "새콤달콤", plantScore = 80, startDate = "16일째", "낑깡", "Good", true),
            Plant(plantName = "길쭉이", plantScore = 0, startDate = "4일째", "바나나","Soso", false),
            Plant(plantName = "작은멋쟁이", plantScore = 0, startDate = "4일째", "방울토마토","Bad", false)
        )

        plantAdapter = context?.let { PlantAdapter(data) }!!
        binding.plantRcv.adapter = plantAdapter

        recyclerView = binding.plantRcv
        recyclerView.layoutManager = GridLayoutManager(context,3)
        recyclerView.adapter = PlantAdapter(data)

        plantAdapter.setItemClickListener(object :PlantAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val bundle = Bundle()
                val plantInfoFragment = PlantInfoFragment()
                plantInfoFragment.arguments = bundle
                activity!!.supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, plantInfoFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}