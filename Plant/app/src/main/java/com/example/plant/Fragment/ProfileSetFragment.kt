package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plant.R
import com.example.plant.databinding.FragmentProfileSetBinding

class ProfileSetFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileSetBinding.inflate(inflater, container, false)

        binding.infoProfileEditToolbar.title = "프로필 관리"

        return binding.root
    }

}