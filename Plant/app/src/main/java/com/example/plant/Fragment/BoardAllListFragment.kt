package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardAllListAdapter
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.databinding.FragmentBoardAllListBinding
import com.example.plant.model.Board


class BoardAllListFragment : Fragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var boardAllListAdapter: BoardAllListAdapter
    private lateinit var data : List<Board>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardAllListBinding.inflate(inflater, container, false)

        data = mutableListOf(Board(
            0, 0, 0,
            "토마토", "Good", "5Kg, 당도 높음", "50,000원", "13:12", "1",
        ),
            Board(
                1, 0, 0,
                "부레옥잠", "Soso", "수상생물", "30,000원", "2023-05-10", "1",
            ),
            Board(
                2, 0, 0,
                "난", "Good", "1M, 사진 참고", "100,000원", "2023-05-08", "1",
            ),
            Board(
                0, 0, 0,
                "토마토", "Good", "5Kg, 당도 높음", "50,000원", "13:12", "1",
            ),
            Board(
                1, 0, 0,
                "부레옥잠", "Soso", "수상생물", "30,000원", "2023-05-10", "1",
            ),
            Board(
                2, 0, 0,
                "난", "Good", "1M, 사진 참고", "100,000원", "2023-05-08", "1",
            ))

        boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
        binding.boardAllListRcv.adapter = boardAllListAdapter

        // val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.boardAllListRcv
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = BoardAllListAdapter(data)
        binding.boardAllListRcv.setHasFixedSize(true)

        return binding.root
    }
}