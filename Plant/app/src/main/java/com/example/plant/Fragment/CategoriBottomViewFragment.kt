package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.plant.R
import com.example.plant.databinding.FragmentCategoriBottomViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CategoriBottomViewFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCategoriBottomViewBinding.inflate(inflater, container, false)
        var state = 0
        var reset = true
        val bundle = Bundle()
        binding.categoriBottomviewVGBt.setOnClickListener {
            state = 5
            setFragmentResult("state", bundleOf("bundleKey" to state))
            parentFragmentManager.beginTransaction()
                .commit()
        }

        binding.categoriBottomviewGBt.setOnClickListener {
            state = 4
            setFragmentResult("state", bundleOf("bundleKey" to state))
            parentFragmentManager.beginTransaction()
                .commit()
        }

        binding.categoriBottomviewSSBt.setOnClickListener {
            state = 3
            setFragmentResult("state", bundleOf("bundleKey" to state))
            parentFragmentManager.beginTransaction()
                .commit()
        }

        binding.categoriBottomviewBBt.setOnClickListener {
            state = 2
            setFragmentResult("state", bundleOf("bundleKey" to state))
            parentFragmentManager.beginTransaction()
                .commit()
        }

        binding.categoriBottomviewVBBt.setOnClickListener {
            state = 1
            setFragmentResult("state", bundleOf("bundleKey" to state))
            parentFragmentManager.beginTransaction()
                .commit()
        }

        binding.categoriBottomviewResetBt.setOnClickListener {
            setFragmentResult("reset", bundleOf("bundleKey" to state))
            parentFragmentManager.beginTransaction()
                .commit()
        }

        binding.categoriBottomviewOkBt.setOnClickListener {
        }
        return binding.root
    }

}