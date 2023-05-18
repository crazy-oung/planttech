package com.example.plant.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.plant.R
import com.example.plant.databinding.FragmentInfoBinding
import com.example.plant.databinding.FragmentInfoTradeBinding

class InfoTradeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoTradeBinding.inflate(inflater, container, false)

        binding.infoBuyMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoPurchaseMoreFragment = InfoPurchaseMoreFragment()
            infoPurchaseMoreFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoPurchaseMoreFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.infoSellMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoSellMoreFragment = InfoSellMoreFragment()
            infoSellMoreFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoSellMoreFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.infoTradeMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoAllTradeListFragment = InfoAllTradeListFragment()
            infoAllTradeListFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoAllTradeListFragment)
                .addToBackStack(null)
                .commit()
        }



        return binding.root
    }
}