package com.example.plant.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.GraphAdapter
import com.example.plant.adapter.HomeAdapter
import com.example.plant.databinding.FragmentGraphBinding

class GraphFragment : Fragment(R.layout.fragment_graph) {

    lateinit var recyclerView : RecyclerView
    lateinit var graphAdapter: GraphAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentGraphBinding.inflate(inflater, container, false)

        val data = listOf(
            listOf(10f, 20f, 30f, 40f, 50f),
            listOf(50f, 40f, 30f, 20f, 10f),
            listOf(30f, 40f, 50f, 40f, 30f)
        )

        recyclerView = binding.graphRcv
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = GraphAdapter(data)

        return binding.root
    }
}