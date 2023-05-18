package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.BoardOtherPlantAdapter
import com.example.plant.adapter.PlantInfoAdapter
import com.example.plant.adapter.PlantInfoGraphAdapter
import com.example.plant.databinding.FragmentBoardInfoBinding
import com.example.plant.databinding.FragmentPlantInfoBinding
import com.example.plant.model.Plant
import com.example.plant.model.PlantMoreInfo


class BoardInfoFragment : Fragment() {
    lateinit var plantInfoRecyclerView : RecyclerView
    lateinit var plantInfoAdapter: PlantInfoAdapter
    lateinit var plantInfoGraphRecyclerView : RecyclerView
    lateinit var plantInfoGraphAdapter: PlantInfoGraphAdapter
    lateinit var boardOtherPlantRecyclerView: RecyclerView
    lateinit var boardOtherPlantAdapter : BoardOtherPlantAdapter
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardInfoBinding.inflate(inflater, container, false)

        binding.boardStateSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.plantStateItemList, R.layout.spinner_item)
        binding.boardStateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {

            }
        }

        val dataFruit = mutableListOf(
            PlantMoreInfo("품종", "화초"),
            PlantMoreInfo("별명", "쭉쭉이"),
            PlantMoreInfo("재배 시작일", "2023-05-10")
        )

        plantInfoAdapter = context?.let { PlantInfoAdapter(dataFruit) }!!
        binding.boardInfoMoreInfoRcv.adapter = plantInfoAdapter

        plantInfoRecyclerView = binding.boardInfoMoreInfoRcv
        plantInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoRecyclerView.adapter = PlantInfoAdapter(dataFruit)
        binding.boardInfoMoreInfoRcv.setHasFixedSize(true)

        val data = listOf(
            listOf(21.2f, 20.4f, 23.2f, 23.1f, 23.8f, 24.1f, 22.1f),
            listOf(33.7f, 34.1f, 32.1f, 35.0f, 32.1f, 33.1f, 34.0f)
        )

        plantInfoGraphAdapter = context?.let { PlantInfoGraphAdapter(data) }!!
        binding.boardInfoQuoteRcv.adapter = plantInfoGraphAdapter

        plantInfoGraphRecyclerView = binding.boardInfoQuoteRcv
        plantInfoGraphRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoGraphRecyclerView.adapter = PlantInfoGraphAdapter(data)
        binding.boardInfoQuoteRcv.setHasFixedSize(true)

        val dataPlant = mutableListOf(
            Plant(plantName = "멋쟁이", plantScore = 100, startDate = "32일째", "토마토","Good", true),
            Plant(plantName = "새콤달콤", plantScore = 80, startDate = "16일째", "낑깡", "Good", true),
            Plant(plantName = "길쭉이", plantScore = 0, startDate = "4일째", "바나나","Soso", false),
            Plant(plantName = "작은멋쟁이", plantScore = 0, startDate = "4일째", "방울토마토","Bad", false)
        )

        boardOtherPlantAdapter = context?.let { BoardOtherPlantAdapter(dataPlant) }!!
        binding.boardOtherPlantRcv.adapter = boardOtherPlantAdapter

        boardOtherPlantRecyclerView = binding.boardOtherPlantRcv
        boardOtherPlantRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        boardOtherPlantRecyclerView.adapter = BoardOtherPlantAdapter(dataPlant)
        binding.boardOtherPlantRcv.setHasFixedSize(true)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}