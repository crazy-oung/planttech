package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil.findBinding
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomeAdapter
import com.example.plant.databinding.FragmentHomeBinding
import com.example.plant.model.Plant
import com.example.plant.model.RecyclerInViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var recyclerView : RecyclerView
    lateinit var mainActivity: MainActivity
    lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val data = mutableListOf(
            Plant(plantName = "토마토", plantScore = 100, startDate = "32일째", "Good"),
            Plant(plantName = "콩나물", plantScore = 80, startDate = "16일째", "Good"),
            Plant(plantName = "바나나", plantScore = 0, startDate = "4일째", "Soso"),
            Plant(plantName = "연꽃", plantScore = 0, startDate = "4일째", "Bad")
        )


        homeAdapter = context?.let { HomeAdapter(data) }!!
        binding.homeRcv.adapter = homeAdapter

        recyclerView = binding.homeRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = homeAdapter

        val itemList = mutableListOf(
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 1),
            RecyclerInViewModel(plantName = "양상추", plantState = "좋음", temp = 20, humidity = 50, type = 2))


        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}