package com.example.plant.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
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
            Plant(plantName = "잘키운 콩나물 팔아요~", plantScore = 5000, startDate = "2023-04-02"),
            Plant(plantName = "관상용 식물 여러종 구매합니다.", plantScore = 100, startDate = "2023-03-30"),
            Plant(plantName = "키위기 쉬운 식물 추천받아요", plantScore = 100, startDate = "2023-03-28"),
            Plant(plantName = "방울 토마토 재배환경 그대로 판매해 봅니다.", plantScore = 50000, startDate = "2023-03-26")
        )

        boardAdapter = context?.let { BoardAdapter(data) }!!
        binding.boardRcv.adapter = boardAdapter

        val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.boardRcv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BoardAdapter(data)


        boardAdapter.setItemClickListener(object : BoardAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_board, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                true
            }
            R.id.delete -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}