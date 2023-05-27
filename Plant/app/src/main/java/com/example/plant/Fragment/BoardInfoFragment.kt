package com.example.plant.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.*
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.api.RetrofitService
import com.example.plant.databinding.FragmentBoardInfoBinding
import com.example.plant.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class BoardInfoFragment : Fragment() {
    lateinit var plantInfoRecyclerView : RecyclerView
    lateinit var plantInfoAdapter: PlantInfoAdapter
    lateinit var plantInfoGraphRecyclerView : RecyclerView
    lateinit var plantInfoGraphAdapter: PlantInfoGraphAdapter
    lateinit var boardOtherPlantRecyclerView: RecyclerView
    lateinit var boardOtherPlantAdapter : BoardOtherPlantAdapter
    lateinit var boardTradeListPlantRecyclerView: RecyclerView
    lateinit var boardTradeListPlantAdapter : BoardTradeListPlantAdapter
    var sellPrice = 0
    var buyPrice = 0
    var productNumber = 0
    /*
    lateinit var boardSellListPlantRecyclerView: RecyclerView
    lateinit var boardSellListPlantAdapter : BoardSellListPlantAdapter
    lateinit var boardBuyListPlantRecyclerView: RecyclerView
    lateinit var boardBuyListPlantAdapter : BoardBuyListPlantAdapter*/
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardInfoBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        val plantNumberResult = arguments?.getInt("plantNumber")
        val plantCategory = arguments?.getString("plantCategory")
        val productNumberResult = arguments?.getInt("productNumber")
        var state = 1
        //val plantNumberResult = 0
        val plantData = mutableListOf<PlantMoreInfo>()
        val decimal = DecimalFormat("#,###")

        binding.boardStateSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.plantStateItemList, R.layout.spinner_item)




        service.plantList(
            0,
            317,
            plantNumberResult!!,
            plantCategory!!,
            "").enqueue(object : Callback<PlantListResponse> {
            override fun onResponse(call: Call<PlantListResponse>, response: Response<PlantListResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponsePlant = response.body()!!
                    Log.d("Gooood", callResponsePlant.toString())
                    Log.d("Gooood", response.headers().toString())

                    for(i in callResponsePlant) {
                        if(i.plantNo == plantNumberResult) {
                            plantData.add(PlantMoreInfo("카테고리", i.plantCategory))
                            plantData.add(PlantMoreInfo("학명", i.plantScientificName))
                            plantData.add(PlantMoreInfo("주요 분포 지역", i.plantOrigin))
                        }
                    }

            service.getProductList(0, 100, "최신순", plantNumberResult, "").enqueue(object : Callback<BoardProductResponse> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<BoardProductResponse>, response: Response<BoardProductResponse>) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        val callResponseBid = response.body()!!
                        Log.d("GoooodBid", callResponseBid.toString())
                        Log.d("GoooodBid", response.headers().toString())

                        binding.boardInfoNameTv.text = callResponseBid[0].productName
                        binding.boardInfoSubNameTv.text = decimal.format(callResponseBid[0].productCount) + "개"
                        bid(5, service, plantNumberResult,
                            binding.boardInfoQuoteRcv,
                            binding.boardInfoTradeListRcv,
                            binding.boardInfoPriceTv,
                            binding.boardInfoCompleteTradeBtn,
                            binding.boardInfoSellTradeBtn,
                            binding.boardInfoBuyTradeBtn,
                            binding.boardInfoTradePriceTv
                        )
                        // 즉시 거래, 판매가
                        buttonTextBuySell(
                            service,
                            plantNumberResult,
                            0,
                            binding.boardInfoSellNowTv,
                            5,
                            binding.boardInfoBuyNowTv
                        )

                        buttonTextBuySell(
                            service,
                            plantNumberResult,
                            1,
                            binding.boardInfoSellNowTv,
                            5,
                            binding.boardInfoBuyNowTv
                        )

                        binding.boardStateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                                        position: Int, id: Long) {

                                when (position) {
                                    0 -> {
                                        bid(5, service, plantNumberResult,
                                            binding.boardInfoQuoteRcv,
                                            binding.boardInfoTradeListRcv,
                                            binding.boardInfoPriceTv,
                                            binding.boardInfoCompleteTradeBtn,
                                            binding.boardInfoSellTradeBtn,
                                            binding.boardInfoBuyTradeBtn,
                                            binding.boardInfoTradePriceTv
                                        )
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            0,
                                            binding.boardInfoSellNowTv,
                                            5,
                                            binding.boardInfoBuyNowTv
                                        )
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            1,
                                            binding.boardInfoSellNowTv,
                                            5,
                                            binding.boardInfoBuyNowTv
                                        )
                                        state = 1
                                    }
                                    1 -> {
                                        bid(4, service, plantNumberResult, binding.boardInfoQuoteRcv,
                                            binding.boardInfoTradeListRcv, binding.boardInfoPriceTv,
                                            binding.boardInfoCompleteTradeBtn,
                                            binding.boardInfoSellTradeBtn,
                                            binding.boardInfoBuyTradeBtn,
                                            binding.boardInfoTradePriceTv)
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            0,
                                            binding.boardInfoSellNowTv,
                                            4,
                                            binding.boardInfoBuyNowTv
                                        )
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            1,
                                            binding.boardInfoSellNowTv,
                                            4,
                                            binding.boardInfoBuyNowTv
                                        )
                                        state = 2
                                    }
                                    2 -> {
                                        bid(3, service, plantNumberResult, binding.boardInfoQuoteRcv,
                                            binding.boardInfoTradeListRcv, binding.boardInfoPriceTv,
                                            binding.boardInfoCompleteTradeBtn,
                                            binding.boardInfoSellTradeBtn,
                                            binding.boardInfoBuyTradeBtn,
                                            binding.boardInfoTradePriceTv)
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            0,
                                            binding.boardInfoSellNowTv,
                                            3,
                                            binding.boardInfoBuyNowTv
                                        )
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            1,
                                            binding.boardInfoSellNowTv,
                                            3,
                                            binding.boardInfoBuyNowTv
                                        )
                                        state = 3
                                    }
                                    3 -> {
                                        bid(2, service, plantNumberResult, binding.boardInfoQuoteRcv,
                                            binding.boardInfoTradeListRcv, binding.boardInfoPriceTv,
                                            binding.boardInfoCompleteTradeBtn,
                                            binding.boardInfoSellTradeBtn,
                                            binding.boardInfoBuyTradeBtn,
                                            binding.boardInfoTradePriceTv)
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            0,
                                            binding.boardInfoSellNowTv,
                                            2,
                                            binding.boardInfoBuyNowTv
                                        )
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            1,
                                            binding.boardInfoSellNowTv,
                                            2,
                                            binding.boardInfoBuyNowTv
                                        )
                                        state = 4
                                    }
                                    4 -> {
                                        bid(1, service, plantNumberResult, binding.boardInfoQuoteRcv,
                                            binding.boardInfoTradeListRcv, binding.boardInfoPriceTv,
                                            binding.boardInfoCompleteTradeBtn,
                                            binding.boardInfoSellTradeBtn,
                                            binding.boardInfoBuyTradeBtn,
                                            binding.boardInfoTradePriceTv)
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            0,
                                            binding.boardInfoSellNowTv,
                                            1,
                                            binding.boardInfoBuyNowTv
                                        )
                                        buttonTextBuySell(
                                            service,
                                            plantNumberResult,
                                            1,
                                            binding.boardInfoSellNowTv,
                                            1,
                                            binding.boardInfoBuyNowTv
                                        )
                                        state = 5
                                    }
                                    else -> {
                                        bid(1, service, plantNumberResult,
                                            binding.boardInfoQuoteRcv,
                                            binding.boardInfoTradeListRcv,
                                            binding.boardInfoPriceTv,
                                            binding.boardInfoCompleteTradeBtn,
                                            binding.boardInfoSellTradeBtn,
                                            binding.boardInfoBuyTradeBtn,
                                            binding.boardInfoTradePriceTv
                                        )
                                    }
                                }
                            }
                        }

                        binding.boardInfoBuyNowBtn.setOnClickListener {
                            // 식물 정보 전달 값 넣어야함
                            val activity = it.context as AppCompatActivity
                            val bundle = Bundle()
                            bundle.putInt("plantNo", plantNumberResult)
                            bundle.putInt("state", state)
                            bundle.putInt("price", buyPrice)
                            bundle.putInt("productNumber", productNumber)
                            val buyFragment = BuyFragment()
                            buyFragment.arguments = bundle
                            activity!!.supportFragmentManager.beginTransaction()
                                .add(R.id.fragmentContainer, buyFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                        plantInfoAdapter = context?.let { PlantInfoAdapter(plantData) }!!
                        binding.boardInfoMoreInfoRcv.adapter = plantInfoAdapter

                        plantInfoRecyclerView = binding.boardInfoMoreInfoRcv
                        plantInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                        plantInfoRecyclerView.adapter = PlantInfoAdapter(plantData)
                        binding.boardInfoMoreInfoRcv.setHasFixedSize(true)
                        // 식물 정보 입력
                        /*
                        service.plantImage(
                            0,
                            20,
                            plantNumberResult,
                            0,
                            "",
                            "최신순").enqueue(object : Callback<PlantImageGetResponse> {
                            override fun onResponse(call: Call<PlantImageGetResponse>, response: Response<PlantImageGetResponse>) {
                                if (response.isSuccessful) {
                                    // 정상적으로 통신이 성공된 경우
                                    val callResponse = response.body()!!
                                    Log.d("Gooood", callResponse.toString())
                                    Log.d("Gooood", response.headers().toString())
/*
                        val test = Base64.decode(callResponse[10].plantColorPic, Base64.DEFAULT)
                        val decode = BitmapFactory.decodeByteArray(test, 0, test.size)

                        binding.boardInfoPhotoIv.setImageBitmap(
                            decode)*/
                                } else {
                                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                                    Log.d("Baaaad", response.toString())
                                }
                            }


                            override fun onFailure(call: Call<PlantImageGetResponse>, t: Throwable) {
                                Log.d("Real Baaaad", "onResponse 대실패")
                            }
                        })*/

                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                    }
                }


                override fun onFailure(call: Call<BoardProductResponse>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }
            })
                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                    }
                }
            override fun onFailure(call: Call<PlantListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })


        // 다른 식물 리스트
        service.plantList(
            0,
            10, // 사이즈 변경 필요
            null,
            plantCategory!!,
            "").enqueue(object : Callback<PlantListResponse>{
            override fun onResponse(
                call: Call<PlantListResponse>,
                response: Response<PlantListResponse>
            ) {
                if(response.isSuccessful){
                    val dataPlant = mutableListOf<Plant>()
                    val callResponsePlant = response.body()!!
                    Log.d("Gooood", callResponsePlant.toString())
                    Log.d("Gooood", response.headers().toString())

                    for(i in callResponsePlant) {
                        dataPlant.add(Plant(i.plantKoreanName, 0, "", i.plantCategory, "", true))
                    }
                    boardOtherPlantAdapter = context?.let { BoardOtherPlantAdapter(dataPlant) }!!
                    binding.boardOtherPlantRcv.adapter = boardOtherPlantAdapter

                    boardOtherPlantRecyclerView = binding.boardOtherPlantRcv
                    boardOtherPlantRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    boardOtherPlantRecyclerView.adapter = BoardOtherPlantAdapter(dataPlant)
                    binding.boardOtherPlantRcv.setHasFixedSize(true)
                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }

            override fun onFailure(call: Call<PlantListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })


        binding.boardInfoBuyNowBtn.setOnClickListener {
            // 식물 정보 전달 값 넣어야함

        }

        binding.boardInfoSellBtn.setOnClickListener {

        }


        // 시세부분


                return binding.root
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
    fun toast(message: String){
        Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show()
    }

    // 최근 체결 거래 목록, 판매 입찰 목록, 구매 입찰 목록, 상태에 따른 가격 및 그래프
    fun bid(
            state : Int,
            service : RetrofitService,
            plantNumber : Int,
            rcv : RecyclerView,
            listRcv : RecyclerView,
            priceTv : TextView,
            completeBtn : Button,
            SellBtn : Button,
            BuyBtn : Button,
            productActiveTv : TextView) {


        productActiveTv.text = "거래가"
        listAdd(
            state,
            service,
            "sold",
            0,
            plantNumber,
            rcv,
            listRcv,
            priceTv
        )

        completeBtn.setOnClickListener {
            productActiveTv.text = "거래가"
            listAdd(
                state,
                service,
                "sold",
                1,
                plantNumber,
                rcv,
                listRcv,
                priceTv
                )
        }

        SellBtn.setOnClickListener {
            productActiveTv.text = "판매가"
            listAdd(
                state,
                service,
                "bid",
                0,
                plantNumber,
                rcv,
                listRcv,
                priceTv
            )
        }

        BuyBtn.setOnClickListener {
            productActiveTv.text = "구매가"
            listAdd(
                state,
                service,
                "bid",
                1,
                plantNumber,
                rcv,
                listRcv,
                priceTv
            )
        }
    }

    fun listAdd(
        state : Int,
        service : RetrofitService,
        productActive : String,
        productType : Int,
        plantNumber : Int,
        rcv : RecyclerView,
        listRcv : RecyclerView,
        priceTv : TextView

        ){
        val test = mutableListOf<Float>()
        val priceSortData =mutableListOf<Float>()
        val decimal = DecimalFormat("#,###")
        val tradeListData = mutableListOf<BoardTradeList>()
        Log.d("test1", productActive)
        Log.d("test2", productType.toString())
        Log.d("test3", plantNumber.toString())
        service.getProductTypeList(
            // state 식물의 특정 상태(등급)의 가격만 받아올 수 있도록
            productActive,
            productType,
            plantNumber
        )
            .enqueue(object : Callback<GetProductTypeListResponse> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<GetProductTypeListResponse>,
                    response: Response<GetProductTypeListResponse>
                ) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        val callResponseBid = response.body()!!
                        Log.d("GoooodBid2", callResponseBid.toString())
                        Log.d("GoooodBid2", response.headers().toString())
                        // 정렬이 되어 있다고 가정

                        for (i in callResponseBid)
                            if(i.plantScoreVal == state)
                                priceSortData.add(i.productPrice.toFloat())
                                //test.add(i.productPrice.toFloat())

                        priceSortData.sort()

                        if(priceSortData.isEmpty())
                            toast("자료가 존재하지 않습니다.")
                        else {
                            priceTv.text = decimal.format(priceSortData[0]) + "원"
                            tradeListData.add(BoardTradeList("Very Good", callResponseBid[0].productPrice, callResponseBid[0].productCount.toString()))
                            tradeListData.add(BoardTradeList("Good", callResponseBid[1].productPrice, callResponseBid[1].productCount.toString()))
                            tradeListData.add(BoardTradeList("Soso", callResponseBid[2].productPrice, callResponseBid[2].productCount.toString()))
                            tradeListData.add(BoardTradeList("Bad", callResponseBid[3].productPrice, callResponseBid[3].productCount.toString()))
                            tradeListData.add(BoardTradeList("Very Bad", callResponseBid[4].productPrice, callResponseBid[4].productCount.toString()))
                        }

/*
                        if (test.isEmpty()) {
                            toast("선택한 상태의 식물이 입찰 목록에 없습니다.")
                            priceTv.text = "-" + "원"
                        }*/



                        service.getBidList(plantNumber, 30, state).enqueue(object : Callback<BidListGetResponse> {
                            override fun onResponse(
                                call: Call<BidListGetResponse>,
                                response: Response<BidListGetResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val callBidResponseBid = response.body()!!
                                    Log.d("GoooodBid2", callBidResponseBid.toString())
                                    Log.d("GoooodBid2", response.headers().toString())

                                    for (i in callBidResponseBid) {
                                        test.add(i.productPrice.toFloat())
                                        Log.d("price", i.productPrice.toFloat().toString())
                                        Log.d("date", i.productBidDate)
                                    }

                                    val data = listOf(test)
                                    plantInfoGraphAdapter = context?.let { PlantInfoGraphAdapter(data) }!!
                                    rcv.adapter = plantInfoGraphAdapter
                                    plantInfoGraphRecyclerView = rcv
                                    plantInfoGraphRecyclerView.layoutManager = LinearLayoutManager(
                                        requireContext(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                    )
                                    plantInfoGraphRecyclerView.adapter = PlantInfoGraphAdapter(data)
                                    rcv.setHasFixedSize(true)

                                } else {
                                    Log.d("BaaaadBid", response.toString())

                                }
                            }
                            override fun onFailure(call: Call<BidListGetResponse>, t: Throwable) {
                                Log.d("Real Baaaad", "onResponse 대실패")
                            }

                        })

                        boardTradeListPlantAdapter = context?.let { BoardTradeListPlantAdapter(tradeListData) }!!
                        listRcv.adapter = boardTradeListPlantAdapter

                        boardTradeListPlantRecyclerView = listRcv
                        boardTradeListPlantRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        boardTradeListPlantRecyclerView.adapter = BoardTradeListPlantAdapter(tradeListData)
                        listRcv.setHasFixedSize(true)



                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        /*
                        Log.d(
                            "Baaaad",
                            NetworkUtil.getErrorResponse(response.errorBody()!!).toString()
                        )*/
                        Log.d("Baaaad1111", response.toString())
                    }
                }


                override fun onFailure(call: Call<GetProductTypeListResponse>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }
            })

    }

    fun buttonTextBuySell(
        service: RetrofitService,
        plantNumberResult : Int,
        productType: Int,
        buyTextView: TextView,
        state: Int,
        sellTextView: TextView){
        val decimal = DecimalFormat("#,###")

        service.getProductGradesList(
            plantNumberResult,
            productType,
            1
            ).enqueue( object : Callback<GetProductGradesListResponse>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<GetProductGradesListResponse>,
                response: Response<GetProductGradesListResponse>
            ) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponse = response.body()!!
                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    for (i in callResponse){
                        if(i.plantScoreNo == state){
                            if(productType == 1) {
                                buyTextView.text = decimal.format(i.productPrice) + "원"
                                buyPrice = i.productPrice
                                productNumber = i.productNo
                            }
                            else {
                                sellTextView.text = decimal.format(i.productPrice) + "원"
                                sellPrice = i.productPrice
                                productNumber = i.productNo
                            }
                        }
                    }

                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }


            override fun onFailure(call: Call<GetProductGradesListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }
        })
    }
}