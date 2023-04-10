package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomeAdapter
import com.example.plant.databinding.FragmentHomeBinding
import com.example.plant.model.RecyclerInViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var recyclerView : RecyclerView
    lateinit var mainActivity: MainActivity
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val data = listOf(30f, 40f, 50f, 40f, 30f)


        homeAdapter = context?.let { HomeAdapter(it, data) }!!
        binding.homeRcv.adapter = homeAdapter

        recyclerView = binding.homeRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = homeAdapter

        val itemList = mutableListOf(
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 1),
            RecyclerInViewModel(plantName = "토마토", plantState = "좋음", temp = 20, humidity = 50, type = 2))

        homeAdapter.datas = itemList
        homeAdapter.notifyDataSetChanged()

        setHasOptionsMenu(true)

        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}