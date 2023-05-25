package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.databinding.FragmentSellBinding

class SellFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSellBinding.inflate(inflater, container, false)

        binding.sellToolbar.title = "구매 진행"
        // 마감기한

        binding.sellDeadlineSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sellDeadlineDate, R.layout.spinner_item)
        binding.sellDeadlineSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {

            }
        }

        binding.sellNowBuyPriceTv.visibility = View.INVISIBLE
        binding.sellDeadlineTv.visibility = View.VISIBLE
        binding.sellDeadlineSpinner.visibility = View.VISIBLE
        binding.sellHopePriceEt.hint = "희망가 입력"

        binding.switchOnOff.isChecked=true
        binding.switchOnOff.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.sellNowBuyPriceTv.visibility = View.INVISIBLE
                binding.sellDeadlineTv.visibility = View.VISIBLE
                binding.sellDeadlineSpinner.visibility = View.VISIBLE
                binding.sellHopePriceEt.hint = "희망가 입력"
            }
            else{
                binding.sellNowBuyPriceTv.visibility = View.VISIBLE
                // 가장 낮은 가격으로
                binding.sellNowBuyPriceTv.text = binding.sellPredictionPriceTv.text.split(" ")[0]
                binding.sellDeadlineTv.visibility = View.INVISIBLE
                binding.sellDeadlineSpinner.visibility = View.INVISIBLE
                binding.sellHopePriceEt.hint = " "
            }
        }

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}