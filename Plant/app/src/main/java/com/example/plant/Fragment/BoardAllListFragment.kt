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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.BoardAllListAdapter
import com.example.plant.adapter.InfoProfileAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.api.RetrofitService
import com.example.plant.databinding.FragmentBoardAllListBinding
import com.example.plant.model.Board
import com.example.plant.model.BoardProductResponse
import com.example.plant.model.PlantListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class BoardAllListFragment : Fragment() {
    lateinit var recyclerView : RecyclerView
    lateinit var boardAllListAdapter: BoardAllListAdapter
    lateinit var mainActivity: MainActivity
    private lateinit var data : MutableList<Board>
    lateinit var category : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardAllListBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        var stateNumber = 5
        val decimal = DecimalFormat("#,###")
        var allProductAmount = 0
        binding.boardAllSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sortShopItemList, R.layout.spinner_item)
        binding.boardAllSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {

            }
        }

        // 상태, 가격에 따른 정렬 필요
        // 검색 기능 필요

        service.getProductList(0, 10, "최신순").enqueue( object : Callback<BoardProductResponse> {
            override fun onResponse(
                call: Call<BoardProductResponse>,
                response: Response<BoardProductResponse>
            ) {
                if(response.isSuccessful){
                    val callPlantResponse = response.body()!!
                    Log.d("Gooood", callPlantResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    for(i in callPlantResponse){
                        plantCategory(i.plantNo, service)
                        data.add(
                            Board(
                                i.plantNo,
                                null,
                                null,
                                i.productName,
                                "상태 when으로",
                                category,
                                decimal.format(i.productPrice) + " 원",
                                null,
                                null,
                                category,
                                i.productCount
                            )
                        )
                        allProductAmount += i.productCount
                    }

                    binding.boardAllListNumTv.text = allProductAmount.toString()



                    // 이름순
                    data.sortBy { it.articleTitle }
                    // 가격순
                    data.sortBy { it.articleProductPrice }
                    // 갯수순
                    data.sortBy { it.plantAmount }
                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }

            override fun onFailure(call: Call<BoardProductResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })
        data = mutableListOf(Board(
            0, 0, 0,
            "토마토", "Good", "5Kg, 당도 높음", "50,000원", "13:12", "1",
            "천남성과"
        ),
            Board(
                1, 0, 1,
                "부레옥잠", "Soso", "수상생물", "30,000원", "2023-05-10", "1",
                "천남성과"
            ))

        boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
        binding.boardAllListRcv.adapter = boardAllListAdapter

        // val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.boardAllListRcv
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = BoardAllListAdapter(data)
        binding.boardAllListRcv.setHasFixedSize(true)


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    fun plantCategory(plantNo : Int, service: RetrofitService){
        service.plantList(0, 10,plantNo).enqueue( object : Callback<PlantListResponse> {
            override fun onResponse(
                call: Call<PlantListResponse>,
                response: Response<PlantListResponse>
            ) {
                if(response.isSuccessful){
                    val callResponse = response.body()!!
                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    category = callResponse[0].plantCategory
                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<PlantListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })
    }
}