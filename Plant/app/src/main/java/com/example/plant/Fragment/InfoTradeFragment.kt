package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plant.R
import com.example.plant.databinding.FragmentInfoBinding
import com.example.plant.databinding.FragmentInfoTradeBinding

class InfoTradeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoTradeBinding.inflate(inflater, container, false)
        return binding.root
    }
}