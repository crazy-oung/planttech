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
import com.example.plant.adapter.BoardAdapter
import com.example.plant.adapter.PlantAdapter
import com.example.plant.databinding.FragmentBoardBinding
import com.example.plant.model.Plant
import java.util.*

class BoardFragment : Fragment(R.layout.fragment_board) {
    lateinit var recyclerView : RecyclerView
    lateinit var boardAdapter: BoardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentBoardBinding.inflate(inflater, container, false)

        val data = mutableListOf(
            Plant(plantName = "토마토", plantScore = 100, startDate = Date(System.currentTimeMillis())),
            Plant(plantName = "콩나물", plantScore = 80, startDate = Date(System.currentTimeMillis())),
            Plant(plantName = "바나나", plantScore = 0, startDate = Date(System.currentTimeMillis()))
        )

        boardAdapter = context?.let { BoardAdapter(data) }!!
        binding.boardRcv.adapter = boardAdapter

        val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.boardRcv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PlantAdapter(data)


        boardAdapter.setItemClickListener(object : BoardAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
            }
        })

        return binding.root
    }
}