package com.example.plant.Fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.adapter.BoardAllListAdapter
import com.example.plant.adapter.PlantInfoAdapter
import com.example.plant.adapter.PlantInfoGraphAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentPlantInfoBinding
import com.example.plant.model.Board
import com.example.plant.model.PlantImageGetResponse
import com.example.plant.model.PlantListResponse
import com.example.plant.model.PlantMoreInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlantInfoFragment : Fragment() {
    lateinit var plantInfoRecyclerView : RecyclerView
    lateinit var plantInfoAdapter: PlantInfoAdapter
    lateinit var plantInfoGraphRecyclerView : RecyclerView
    lateinit var plantInfoGraphAdapter: PlantInfoGraphAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantInfoBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        val plantName = arguments?.getString("plantName")
        val plantNo = arguments?.getInt("plantNo")
        val plantState = arguments?.getString("state")

        binding.switchOnOff.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                service.plantImage(0,10, null, 1, null).enqueue( object : Callback<PlantImageGetResponse> {
                    override fun onResponse(
                        call: Call<PlantImageGetResponse>,
                        response: Response<PlantImageGetResponse>
                    ) {
                        if(response.isSuccessful){
                            val callPhotoResponse = response.body()!!
                            Log.d("Gooood", callPhotoResponse.toString())
                            Log.d("Gooood", response.headers().toString())
                            Log.d("colorNumber", callPhotoResponse[0].plantWarehouseNo.toString())
                            Log.d("plantColorPic", callPhotoResponse[0].plantColorPic)
                            binding.plantInfoCameraBtn.setOnClickListener {
                                val test = Base64.decode(callPhotoResponse[0].plantColorPic, Base64.DEFAULT)
                                val decode = BitmapFactory.decodeByteArray(test, 0, test.size)

                                binding.plantInfoCameraStateTv.text = "색채 분석 상태 : " +callPhotoResponse[0].plantColorGrade
                                binding.plantInfoCameraIv.setImageBitmap(decode)
                            }
                        } else {
                            Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                            Log.d("Baaaad", response.toString())
                        }
                    }

                    override fun onFailure(call: Call<PlantImageGetResponse>, t: Throwable) {
                        Log.d("Real Baaaad", "onResponse 대실패")
                    }

                })
            } else {

            }
        }



        val dataFruit = mutableListOf(
            PlantMoreInfo("품종", "화초"),
            PlantMoreInfo("별명", "쭉쭉이"),
            PlantMoreInfo("재배 시작일", "2023-05-10")
        )

        plantInfoAdapter = context?.let { PlantInfoAdapter(dataFruit) }!!
        binding.plantInfoMoreInfoRcv.adapter = plantInfoAdapter

        plantInfoRecyclerView = binding.plantInfoMoreInfoRcv
        plantInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoRecyclerView.adapter = PlantInfoAdapter(dataFruit)
        binding.plantInfoMoreInfoRcv.setHasFixedSize(true)

        val data = listOf(
            listOf(21.2f, 20.4f, 23.2f, 23.1f, 23.8f, 24.1f, 22.1f),
            listOf(33.7f, 34.1f, 32.1f, 35.0f, 32.1f, 33.1f, 34.0f)
        )

        plantInfoGraphAdapter = context?.let { PlantInfoGraphAdapter(data) }!!
        binding.plantInfoTempAndHumiRcv.adapter = plantInfoGraphAdapter

        plantInfoGraphRecyclerView = binding.plantInfoTempAndHumiRcv
        plantInfoGraphRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoGraphRecyclerView.adapter = PlantInfoGraphAdapter(data)
        binding.plantInfoTempAndHumiRcv.setHasFixedSize(true)

        return binding.root
    }
}