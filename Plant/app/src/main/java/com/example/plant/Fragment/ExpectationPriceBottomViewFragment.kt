package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.PlantInfoGraphAdapter
import com.example.plant.adapter.PriceExpGraphAdapter
import com.example.plant.databinding.FragmentExpectationBottomViewBinding
import com.example.plant.databinding.FragmentPriceBottomViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DecimalFormat

class ExpectationPriceBottomViewFragment : BottomSheetDialogFragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var priceExpGraphAdapter: PriceExpGraphAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExpectationBottomViewBinding.inflate(inflater, container, false)

        val plantWHNo = arguments?.getInt("plantWHNo")
        val expPriceData = mutableListOf(
            mutableListOf("114", "spring", "very good", "1139.2725"), mutableListOf("115", "spring", "soso", "844.20996"), mutableListOf("98", "spring", "good", "1021.8635"), mutableListOf("99", "spring", "very good", "1180.2537"), mutableListOf("100", "spring", "very good", "1134.9269"), mutableListOf("101", "spring", "good", "1029.7191"), mutableListOf("102", "spring", "soso", "838.9033"), mutableListOf("103", "spring", "very good", "1113.815"), mutableListOf("104", "spring", "very good", "1177.7699"), mutableListOf("105", "spring", "bad", "674.39417"), mutableListOf("106", "spring", "good", "1061.5631"), mutableListOf("107", "spring", "very good", "1160.3835"), mutableListOf("108", "spring", "bad", "718.90186"), mutableListOf("109", "spring", "soso", "866.31647"), mutableListOf("110", "spring", "soso", "897.70807"), mutableListOf("111", "spring", "very good", "1128.0967"), mutableListOf("112", "spring", "very bad", "449.5867"), mutableListOf("113", "spring", "good", "1025.4326"), mutableListOf("82", "spring", "soso", "867.6429"), mutableListOf("83", "spring", "bad", "668.5368"), mutableListOf("84", "spring", "very bad", "448.88165"), mutableListOf("85", "spring", "good", "1027.27"), mutableListOf("86", "spring", "soso", "865.87445"), mutableListOf("87", "spring", "good", "1030.9438"), mutableListOf("88", "spring", "bad", "706.4085"), mutableListOf("89", "spring", "very good", "1146.7234"), mutableListOf("90", "spring", "good", "1035.8433"), mutableListOf("91", "spring", "soso", "916.2774"), mutableListOf("92", "spring", "soso", "880.9064"), mutableListOf("93", "spring", "bad", "675.17505"), mutableListOf("94", "spring", "good", "1057.889"), mutableListOf("95", "spring", "very bad", "496.12427"), mutableListOf("96", "spring", "soso", "907.8775"), mutableListOf("97", "spring", "good", "1054.2147"), mutableListOf("68", "spring", "bad", "714.21674"), mutableListOf("69", "spring", "very good", "1133.685"), mutableListOf("70", "spring", "bad", "719.68365"), mutableListOf("71", "spring", "very good", "1175.9058"), mutableListOf("72", "spring", "very bad", "458.75317"), mutableListOf("73", "spring", "very bad", "556.05774"), mutableListOf("74", "spring", "soso", "896.382"), mutableListOf("75", "spring", "good", "1054.2147"), mutableListOf("76", "spring", "very bad", "520.0982"), mutableListOf("77", "spring", "good", "1017.8108"), mutableListOf("78", "spring", "very good", "1152.3123"), mutableListOf("79", "spring", "soso", "872.94806"), mutableListOf("80", "spring", "bad", "686.107"), mutableListOf("81", "spring", "very bad", "431.95938"), mutableListOf("66", "spring", "good", "1038.2926"), mutableListOf("67", "spring", "soso", "874.2748"), mutableListOf("62", "spring", "soso", "896.82477"), mutableListOf("63", "spring", "bad", "682.98364"), mutableListOf("64", "spring", "soso", "852.60956"), mutableListOf("65", "spring", "bad", "681.03125"), mutableListOf("52", "spring", "very good", "1148.5869"), mutableListOf("53", "spring", "very good", "1167.8352"), mutableListOf("54", "spring", "soso", "872.50604"), mutableListOf("55", "spring", "very bad", "543.36615"), mutableListOf("56", "spring", "bad", "715.38806"), mutableListOf("57", "spring", "very bad", "444.65042"), mutableListOf("58", "spring", "bad", "698.60004"), mutableListOf("59", "spring", "soso", "838.4617"), mutableListOf("60", "spring", "very bad", "435.48413"), mutableListOf("61", "spring", "very bad", "479.90616"), mutableListOf("34", "spring", "very good", "1141.7563"), mutableListOf("35", "spring", "very bad", "484.1372"), mutableListOf("36", "spring", "soso", "841.5568"), mutableListOf("37", "spring", "soso", "887.0977"), mutableListOf("38", "spring", "very good", "1190.8093"), mutableListOf("39", "spring", "very good", "1126.8551"), mutableListOf("40", "spring", "good", "1040.7422"), mutableListOf("41", "spring", "very good", "1172.1813"), mutableListOf("42", "spring", "good", "1018.39"), mutableListOf("43", "spring", "soso", "870.738"), mutableListOf("44", "spring", "very good", "1115.0574"), mutableListOf("45", "spring", "good", "1055.4391"), mutableListOf("46", "spring", "bad", "663.85315"), mutableListOf("47", "spring", "very good", "1185.2205"), mutableListOf("48", "spring", "soso", "849.5155"), mutableListOf("49", "spring", "soso", "891.0765"), mutableListOf("50", "spring", "bad", "700.1618"), mutableListOf("51", "spring", "good", "1058.5012"), mutableListOf("17", "spring", "good", "1007.96954"), mutableListOf("18", "spring", "soso", "831.8294"), mutableListOf("19", "spring", "soso", "869.8529"), mutableListOf("20", "spring", "soso", "887.53937"), mutableListOf("21", "spring", "soso", "840.6724"), mutableListOf("22", "spring", "soso", "846.86237"), mutableListOf("23", "spring", "bad", "652.76825"), mutableListOf("24", "spring", "good", "1042.579"), mutableListOf("25", "spring", "soso", "875.1592"), mutableListOf("26", "spring", "soso", "914.0674"), mutableListOf("27", "spring", "very good", "1138.6522"), mutableListOf("28", "spring", "very good", "1163.4886"), mutableListOf("29", "spring", "good", "1037.0681"), mutableListOf("30", "spring", "soso", "845.09393"), mutableListOf("31", "spring", "very bad", "540.5456"), mutableListOf("32", "spring", "good", "1018.39"), mutableListOf("33", "spring", "very good", "1108.8479"), mutableListOf("1", "spring", "good", "1057.2766"), mutableListOf("2", "spring", "very bad", "465.0994"), mutableListOf("3", "spring", "soso", "847.7467"), mutableListOf("4", "spring", "good", "1059.7264"), mutableListOf("5", "spring", "bad", "665.41455"), mutableListOf("6", "spring", "bad", "652.3999"), mutableListOf("7", "spring", "very good", "1116.9202"), mutableListOf("8", "spring", "soso", "887.0977"), mutableListOf("9", "spring", "bad", "673.61346"), mutableListOf("10", "spring", "soso", "914.50903"), mutableListOf("11", "spring", "bad", "670.4901"), mutableListOf("12", "spring", "good", "1052.99"), mutableListOf("13", "spring", "bad", "719.29266"), mutableListOf("14", "spring", "very bad", "514.45703"), mutableListOf("15", "spring", "good", "1059.1139"), mutableListOf("16", "spring", "soso", "865.87445")
        )
        val decimal = DecimalFormat("#,###")

        Log.d("plantNoTest", plantWHNo.toString())
        for ( i in expPriceData ){
            binding.plantInfoPriceExpTv.text = "예측 가격 : " + decimal.format(i[3].toFloat().toInt())
        }

/*
        priceExpGraphAdapter = context?.let { PriceExpGraphAdapter(data) }!!
        binding.plantInfoPriceExpRcv.adapter = priceExpGraphAdapter

        recyclerView = binding.plantInfoPriceExpRcv
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = PriceExpGraphAdapter(data)
        binding.plantInfoPriceExpRcv.setHasFixedSize(true)
*/
        return binding.root
    }

}