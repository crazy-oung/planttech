package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.databinding.FragmentInfoPurchaseListOneBinding
import com.example.plant.databinding.FragmentInfoPurchaseListThreeBinding

class InfoPurchaseListThreeFragment : Fragment() {
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoPurchaseListThreeBinding.inflate(inflater, container, false)

        binding.infoPurchaseSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sortPurchaseItemList, R.layout.spinner_item)
        binding.infoPurchaseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {

            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

}