package com.example.plant.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardAdapter
import com.example.plant.adapter.BoardContentAdapter
import com.example.plant.adapter.PlantAdapter
import com.example.plant.databinding.FragmentBoardBinding
import com.example.plant.model.Plant
import java.util.*

class BoardContentFragment : Fragment(R.layout.board_content) {
    lateinit var recyclerView : RecyclerView
    lateinit var boardContentAdapter: BoardContentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentBoardBinding.inflate(inflater, container, false)
/*
        boardContnetAdapter = context?.let { BoardAdapter(data) }!!
        binding.boardRcv.adapter = boardContentAdapter
*/

        recyclerView = binding.boardRcv
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.adapter = BoardContentAdapter(data)



        return binding.root
    }

}