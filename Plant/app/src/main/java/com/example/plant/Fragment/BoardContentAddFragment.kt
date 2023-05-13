package com.example.plant.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardContentAdapter
import com.example.plant.adapter.BoardContentAddAdapter
import com.example.plant.databinding.FragmentBoardContentAddBinding
import com.example.plant.databinding.FragmentBoardContentBinding
import com.example.plant.model.Board
import com.example.plant.model.BoardComment


class BoardContentAddFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var boardContentAddAdapter: BoardContentAddAdapter
    lateinit var boardData : MutableList<Board>
    lateinit var data : MutableList<BoardComment>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBoardContentAddBinding.inflate(inflater, container, false)

        return binding.root
    }
}