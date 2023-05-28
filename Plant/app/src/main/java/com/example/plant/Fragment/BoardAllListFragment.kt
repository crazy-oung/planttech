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
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
class BoardAllListFragment : Fragment() {
    val priceBottomSheet: LinearLayout by lazy{ mainActivity.findViewById<LinearLayout>(R.id.price_bottomview)}
    val categoriBottomSheet: LinearLayout by lazy{ mainActivity.findViewById<LinearLayout>(R.id.price_bottomview)}
    lateinit var priceSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var categoriSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var recyclerView : RecyclerView
    lateinit var boardAllListAdapter: BoardAllListAdapter
    lateinit var mainActivity: MainActivity
    lateinit var category : String
    var data = mutableListOf<Board>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardAllListBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        var stateNumber = 5
        val decimal = DecimalFormat("#,###")
        var allProductAmount = 0
        var plantCategory = mutableListOf<String>(
            "수선화과",
            "닭의장풀과",
            "두릅나무과",
            "두릅나무과",
            "포도과",
            "천남성과",
            "천남성과",
            "천남성과"
        )
        var plantNumber = mutableListOf<Int>(10, 9, 8, 4, 3, 2, 1, 0)

        var plantCount = 0
        var sortNo = 0
        var sortReset = false
        var priceMin = 0
        var priceMax = 1000000

        /*
        priceSheetBehavior = BottomSheetBehavior.from(mainActivity.findViewById(R.id.price_bottomview))
        priceSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                priceBottomSheet.findViewById<Button>(R.id.price_bottomview_ok_bt).setOnClickListener {
                    priceSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    sortReset = false
                }
                priceBottomSheet.findViewById<Button>(R.id.price_bottomview_reset_bt).setOnClickListener {
                    priceSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    sortReset = true
                }

                when(newState){
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        priceMin = priceBottomSheet.findViewById<EditText>(R.id.price_bottomview_min_et).text.toString().toInt()
                        priceMax = priceBottomSheet.findViewById<EditText>(R.id.price_bottomview_max_et).text.toString().toInt()

                        if(sortReset) {
                            priceMin = 0
                            priceMax = 1000000
                        }
                        for ( i in data){
                            val priceText = i.articleProductPrice!!.replace("[^0-9]".toRegex(), "").toInt()
                            if (priceText < priceMin && priceText > priceMax)
                                data.remove(i)
                        }
                        boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
                        binding.boardAllListRcv.adapter = boardAllListAdapter

                        // val intent = Intent(this.context, CameraFragment::class.java)
                        recyclerView = binding.boardAllListRcv
                        recyclerView.layoutManager = GridLayoutManager(context, 2)
                        recyclerView.adapter = BoardAllListAdapter(data)
                        binding.boardAllListRcv.setHasFixedSize(true)

                    }
                }

            }



            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                TODO("Not yet implemented")
            }

        })
        categoriSheetBehavior = BottomSheetBehavior.from(categoriBottomSheet)
        */

        binding.boardAllPriceBtn.setOnClickListener {
            val priceBottomSheet = PriceBottomViewFragment()
            priceBottomSheet.show(mainActivity.supportFragmentManager, priceBottomSheet.tag)
        }

        binding.boardAllStateBtn.setOnClickListener {
            val categoriBottomSheet = CategoriBottomViewFragment()
            categoriBottomSheet.show(mainActivity.supportFragmentManager, categoriBottomSheet.tag)
        }



        binding.boardAllSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sortShopItemList, R.layout.spinner_item)


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
/*
                    for (i in callPlantResponse){
                        plantNumber.add(i.plantNo)
                        plantCategory(i.plantNo, service)
                    }
*/
                                for(i in callPlantResponse){
                                    allProductAmount += i.productCount

                                    data.add(
                                        Board(
                                            i.plantNo,
                                            null,
                                            i.plantNo,
                                            i.productName,
                                            "Very Good",
                                            plantCategory[plantCount],
                                            decimal.format(i.productPrice) + " 원",
                                            null,
                                            null,
                                            plantCategory[plantCount],
                                            allProductAmount
                                        )
                                    )
                                    plantCount += 1
                                }

                    binding.boardAllSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
                            binding.boardAllListRcv.adapter = boardAllListAdapter

                            // val intent = Intent(this.context, CameraFragment::class.java)
                            recyclerView = binding.boardAllListRcv
                            recyclerView.layoutManager = GridLayoutManager(context, 2)
                            recyclerView.adapter = BoardAllListAdapter(data)
                            binding.boardAllListRcv.setHasFixedSize(true)
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                                    position: Int, id: Long) {
                            when(position){

                                0 -> {
                                    data.sortBy { it.articleTitle }
                                    boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
                                    binding.boardAllListRcv.adapter = boardAllListAdapter

                                    // val intent = Intent(this.context, CameraFragment::class.java)
                                    recyclerView = binding.boardAllListRcv
                                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                                    recyclerView.adapter = BoardAllListAdapter(data)
                                    binding.boardAllListRcv.setHasFixedSize(true)
                                }

                                1 -> {
                                    data.sortBy { it.articleProductPrice }
                                    boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
                                    binding.boardAllListRcv.adapter = boardAllListAdapter

                                    // val intent = Intent(this.context, CameraFragment::class.java)
                                    recyclerView = binding.boardAllListRcv
                                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                                    recyclerView.adapter = BoardAllListAdapter(data)
                                    binding.boardAllListRcv.setHasFixedSize(true)
                                }

                                2 -> {
                                    data.sortBy { it.plantAmount }
                                    data.reverse()
                                    boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
                                    binding.boardAllListRcv.adapter = boardAllListAdapter

                                    // val intent = Intent(this.context, CameraFragment::class.java)
                                    recyclerView = binding.boardAllListRcv
                                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                                    recyclerView.adapter = BoardAllListAdapter(data)
                                    binding.boardAllListRcv.setHasFixedSize(true)
                                }
                            }
                        }
                    }

                    data.sortBy { it.articleTitle }
                    boardAllListAdapter = context?.let { BoardAllListAdapter(data) }!!
                    binding.boardAllListRcv.adapter = boardAllListAdapter

                    // val intent = Intent(this.context, CameraFragment::class.java)
                    recyclerView = binding.boardAllListRcv
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    recyclerView.adapter = BoardAllListAdapter(data)
                    binding.boardAllListRcv.setHasFixedSize(true)
                    binding.boardAllListNumTv.text = allProductAmount.toString()


/*
                    // 이름순
                    data.sortBy { it.articleTitle }
                    // 가격순
                    data.sortBy { it.articleProductPrice }
                    // 갯수순
                    data.sortBy { it.plantAmount }*/
                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }

            override fun onFailure(call: Call<BoardProductResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })


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
/*
                    for (i in callResponse){
                        plantCategory.add(i.plantCategory)
                    }*/
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