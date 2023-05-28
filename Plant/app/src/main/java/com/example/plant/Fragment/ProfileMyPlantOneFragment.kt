package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.ProfilePlantListMoreInfoAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentProfileMyPlantOneBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileMyPlantOneFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var recyclerView : RecyclerView
    lateinit var profilePlantAdapter: ProfilePlantListMoreInfoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileMyPlantOneBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        var state = "Very Good"
        var count = 0
        val plantNickname = listOf<String>("뾰족이", "공기청정기", "나난나" )
        binding.infoPlantMoreOneSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sortPlantItemList, R.layout.spinner_item)
        binding.infoPlantMoreOneSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {

            }
        }

        service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse> {
            override fun onResponse(
                call: Call<GetUserPlantResponse>,
                response: Response<GetUserPlantResponse>
            ) {
                if (response.isSuccessful){
                    val plantResponse = response.body()!!

                    Log.d("Gooood", plantResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    var data = mutableListOf<GetUserPlantResponseItem>()
                    for (i in plantResponse) {
                        data.add(
                            GetUserPlantResponseItem(
                                i.plantCategory,
                                null, null,null,null,
                                i.plantKoreanName,
                                null,
                                i.plantNo,
                                null, null,
                                0, //유저 정보 넣어야함
                                null,null,null,
                                plantNickname[count],
                                null,
                                i.warehousePlantNo
                            )
                        )
                        count += 1
                    }
                    profilePlantAdapter = context?.let { ProfilePlantListMoreInfoAdapter(data) }!!
                    binding.profilePlantMoreinfoOneRcv.adapter = profilePlantAdapter

                    recyclerView = binding.profilePlantMoreinfoOneRcv
                    recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = profilePlantAdapter
                    binding.profilePlantMoreinfoOneRcv.setHasFixedSize(true)

                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })





        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}