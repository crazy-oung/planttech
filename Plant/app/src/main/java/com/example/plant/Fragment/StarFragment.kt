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
import com.example.plant.adapter.FruitAdapter
import com.example.plant.adapter.OrnaAdapter
import com.example.plant.adapter.StarAdapter
import com.example.plant.adapter.VegiAdapter
import com.example.plant.databinding.FragmentStarBinding
import com.example.plant.model.Plant

class StarFragment : Fragment() {
    lateinit var fruitRecyclerView : RecyclerView
    lateinit var vegiRecyclerView : RecyclerView
    lateinit var ornaRecyclerView : RecyclerView
    lateinit var fruitAdapter: FruitAdapter
    lateinit var vegiAdapter: VegiAdapter
    lateinit var ornaAdapter: OrnaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStarBinding.inflate(inflater, container, false)

        val dataFruit = mutableListOf(
            Plant(plantName = "멋쟁이", plantScore = 100, startDate = "32일째", "토마토","Good", true),
            Plant(plantName = "새콤달콤", plantScore = 80, startDate = "16일째", "낑깡", "Good", true)
        )

        val dataVegi = mutableListOf(
            Plant(plantName = "쭉쭉이", plantScore = 100, startDate = "32일째", "콩나물","Good", true),
            Plant(plantName = "보쌈", plantScore = 80, startDate = "16일째", "적상추", "Good", true)
        )

        val dataOrna = mutableListOf(
            Plant(plantName = "가시가시", plantScore = 100, startDate = "32일째", "장미","Good", true)
        )

        fruitAdapter = context?.let { FruitAdapter(dataFruit) }!!
        binding.homeStarFruitRcv.adapter = fruitAdapter

        fruitRecyclerView = binding.homeStarFruitRcv
        fruitRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fruitRecyclerView.adapter = FruitAdapter(dataFruit)
        binding.homeStarFruitRcv.setHasFixedSize(true)



        vegiAdapter = context?.let { VegiAdapter(dataVegi) }!!
        binding.homeStarVegiRcv.adapter = vegiAdapter

        vegiRecyclerView = binding.homeStarVegiRcv
        vegiRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        vegiRecyclerView.adapter = VegiAdapter(dataVegi)
        binding.homeStarVegiRcv.setHasFixedSize(true)


        ornaAdapter = context?.let { OrnaAdapter(dataOrna) }!!
        binding.homeStarOrnaRcv.adapter = ornaAdapter

        ornaRecyclerView = binding.homeStarOrnaRcv
        ornaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        ornaRecyclerView.adapter = OrnaAdapter(dataOrna)
        binding.homeStarOrnaRcv.setHasFixedSize(true)

        return binding.root
    }
}