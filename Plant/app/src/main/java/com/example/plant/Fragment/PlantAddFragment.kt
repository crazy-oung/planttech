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
import com.example.plant.databinding.FragmentPlantAddBinding
import com.example.plant.databinding.FragmentProfileSetBinding

class PlantAddFragment : Fragment() {
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantAddBinding.inflate(inflater, container, false)

        binding.infoProfileEditToolbar.title = "식물 추가"

        binding.plantAddCategori1Spinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.plantCategory1ItemList, R.layout.spinner_item)
        binding.plantAddCategori1Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {

            }
        }

        binding.plantAddCategori2Spinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.plantCategory2ItemList, R.layout.spinner_item)
        binding.plantAddCategori2Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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