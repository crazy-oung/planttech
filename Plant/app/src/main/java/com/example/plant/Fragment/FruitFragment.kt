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
import com.example.plant.databinding.FragmentAllBinding
import com.example.plant.databinding.FragmentFruitBinding
import com.example.plant.model.Plant

class FruitFragment : Fragment() {
    lateinit var fruitRecyclerView : RecyclerView
    lateinit var fruitAdapter: FruitAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFruitBinding.inflate(inflater, container, false)

        val dataFruit = mutableListOf(
            Plant(plantName = "멋쟁이", plantScore = 100, startDate = "32일째", "토마토","Good", true),
            Plant(plantName = "새콤달콤", plantScore = 80, startDate = "16일째", "낑깡", "Good", true),
            Plant(plantName = "길쭉이", plantScore = 0, startDate = "4일째", "바나나","Soso", false),
            Plant(plantName = "작은멋쟁이", plantScore = 0, startDate = "4일째", "방울토마토","Bad", false)
        )

        fruitAdapter = context?.let { FruitAdapter(dataFruit) }!!
        binding.fruitRcv.adapter = fruitAdapter

        fruitRecyclerView = binding.fruitRcv
        fruitRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fruitRecyclerView.adapter = FruitAdapter(dataFruit)
        binding.fruitRcv.setHasFixedSize(true)
        return binding.root
    }

}