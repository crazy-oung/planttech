package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.adapter.FruitAdapter
import com.example.plant.adapter.OrnaAdapter
import com.example.plant.adapter.VegiAdapter
import com.example.plant.databinding.FragmentAllBinding
import com.example.plant.model.Plant

class AllFragment : Fragment() {
    lateinit var fruitRecyclerView : RecyclerView
    lateinit var vegiRecyclerView : RecyclerView
    lateinit var ornaRecyclerView : RecyclerView
    lateinit var fruitAdapter: FruitAdapter
    lateinit var vegiAdapter: VegiAdapter
    lateinit var ornaAdapter: OrnaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAllBinding.inflate(inflater, container, false)

        val dataFruit = mutableListOf(
            Plant(plantName = "멋쟁이", plantScore = 100, startDate = "32일째", "토마토","Good", true),
            Plant(plantName = "새콤달콤", plantScore = 80, startDate = "16일째", "낑깡", "Good", true),
            Plant(plantName = "길쭉이", plantScore = 0, startDate = "4일째", "바나나","Soso", false),
            Plant(plantName = "작은멋쟁이", plantScore = 0, startDate = "4일째", "방울토마토","Bad", false)
        )

        val dataVegi = mutableListOf(
            Plant(plantName = "쭉쭉이", plantScore = 100, startDate = "32일째", "콩나물","Good", true),
            Plant(plantName = "보쌈", plantScore = 80, startDate = "16일째", "적상추", "Good", true)
        )

        val dataOrna = mutableListOf(
            Plant(plantName = "가시가시", plantScore = 100, startDate = "32일째", "장미","Good", false),
            Plant(plantName = "할미", plantScore = 80, startDate = "16일째", "할미꽃", "Good", false)
        )


        fruitAdapter = context?.let { FruitAdapter(dataFruit) }!!
        binding.homeFruitRcv.adapter = fruitAdapter

        fruitRecyclerView = binding.homeFruitRcv
        fruitRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fruitRecyclerView.adapter = FruitAdapter(dataFruit)
        binding.homeFruitRcv.setHasFixedSize(true)

        vegiAdapter = context?.let { VegiAdapter(dataVegi) }!!
        binding.homeVegiRcv.adapter = vegiAdapter

        vegiRecyclerView = binding.homeVegiRcv
        vegiRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        vegiRecyclerView.adapter = VegiAdapter(dataVegi)
        binding.homeVegiRcv.setHasFixedSize(true)


        ornaAdapter = context?.let { OrnaAdapter(dataOrna) }!!
        binding.homeOrnaRcv.adapter = ornaAdapter

        ornaRecyclerView = binding.homeOrnaRcv
        ornaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        ornaRecyclerView.adapter = OrnaAdapter(dataOrna)
        binding.homeOrnaRcv.setHasFixedSize(true)

        return binding.root
    }

}