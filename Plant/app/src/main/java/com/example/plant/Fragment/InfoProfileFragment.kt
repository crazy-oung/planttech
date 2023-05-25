package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardAdapter
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoProfileBinding
import com.example.plant.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoProfileFragment : Fragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var infoProfileAdapter: InfoProfileAdapter
    private lateinit var data : List<Board>
    private lateinit var plantCategory : PlantCategoryResponse
    private lateinit var plantListRequest: PlantListRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoProfileBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()


        // 유저 플랜트 정보 목록
        service.getUserPlant().enqueue(object  : Callback<GetUserPlantResponse>{
            override fun onResponse(
                call: Call<GetUserPlantResponse>,
                response: Response<GetUserPlantResponse>
            ) {
                if (response.isSuccessful){
                    val upResponse = response.body()!!

                    binding.infoProfilePlantAllTv.text = upResponse.size.toString()
                    Log.d("Gooood", upResponse.toString())
                    Log.d("Gooood", response.headers().toString())


                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<GetUserPlantResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })



        service.plantCategory().enqueue(object : Callback<PlantCategoryResponse> {
            override fun onResponse(call: Call<PlantCategoryResponse>, response: Response<PlantCategoryResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val plantResponse = response.body()!!
                    var num = 0

                    for(i in plantResponse)
                        num += i.plantCategoryNo

                    binding.infoProfilePlantAllTv.text = num.toString()

                    Log.d("Gooood", plantResponse.toString())
                    Log.d("Gooood", response.headers().toString())


                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }



            override fun onFailure(call: Call<PlantCategoryResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })



        data = mutableListOf(Board(
            0, 0, 0,
            "토마토", "Good", "5Kg, 당도 높음", "50,000원", "13:12", "1",
        ),
            Board(
                1, 0, 0,
                "부레옥잠", "Soso", "수상생물", "30,000원", "2023-05-10", "1",
            ),
            Board(
                2, 0, 0,
                "난", "Good", "1M, 사진 참고", "100,000원", "2023-05-08", "1",
            ),
            Board(
                0, 0, 0,
                "토마토", "Good", "5Kg, 당도 높음", "50,000원", "13:12", "1",
            ),
            Board(
                1, 0, 0,
                "부레옥잠", "Soso", "수상생물", "30,000원", "2023-05-10", "1",
            ),
            Board(
                2, 0, 0,
                "난", "Good", "1M, 사진 참고", "100,000원", "2023-05-08", "1",
            ))

        infoProfileAdapter = context?.let { InfoProfileAdapter(data) }!!
        binding.infoProfileMysellRcv.adapter = infoProfileAdapter

        recyclerView = binding.infoProfileMysellRcv
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = InfoProfileAdapter(data)
        binding.infoProfileMysellRcv.setHasFixedSize(true)

        binding.infoPlantMoreBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val infoPlantMoreFragment = InfoPlantMoreFragment()
            infoPlantMoreFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, infoPlantMoreFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}