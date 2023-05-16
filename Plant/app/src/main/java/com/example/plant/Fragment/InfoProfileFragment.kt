package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardAdapter
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.databinding.FragmentInfoProfileBinding
import com.example.plant.model.Board

class InfoProfileFragment : Fragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var infoProfileAdapter: InfoProfileAdapter
    private lateinit var data : List<Board>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoProfileBinding.inflate(inflater, container, false)

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

        infoProfileAdapter = context?.let { InfoProfileAdapter(data) }!!
        binding.infoProfileMysellRcv.adapter = infoProfileAdapter

        // val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.infoProfileMysellRcv
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = InfoProfileAdapter(data)
        binding.infoProfileMysellRcv.setHasFixedSize(true)

        return binding.root
    }

}