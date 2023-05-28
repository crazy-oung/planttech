package com.example.plant.Fragment

import android.app.Notification
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentBuyBinding
import com.example.plant.model.PlantListResponse
import com.example.plant.model.PostBidRequest
import com.example.plant.model.UserMeResponse
import com.example.plant.model.UserNotificationPostRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class BuyFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBuyBinding.inflate(inflater, container, false)
        val plantNumberResult = arguments?.getInt("plantNo")
        val stateResult = arguments?.getInt("state")
        val priceResult = arguments?.getInt("price")
        val productNumberResult = arguments?.getInt("productNumber")
        var stateText = "Very Good"
        val decimal = DecimalFormat("#,###")
        val bidDate = ""
        val service = ApiClient.getApiInterface()
        var today = Calendar.getInstance()
        today.time = Date()
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd 00:00:00" + ".000Z")

        Log.d("productNumber test", productNumberResult.toString())
        when(stateResult){
            1 -> stateText = "Very Good"
            2 -> stateText = "Good"
            3 -> stateText = "Soso"
            4 -> stateText = "Bad"
            5 -> stateText = "Very Bad"
        }
        binding.buyToolbar.title = "구매 진행"
        // 마감기한

        service.plantList(
            0,
            317,
            plantNumberResult!!).enqueue(object : Callback<PlantListResponse> {
            override fun onResponse(
                call: Call<PlantListResponse>,
                response: Response<PlantListResponse>
            ) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponse = response.body()!!
                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())
                    binding.buyPlantNickNameTv.text = callResponse[0].plantKoreanName
                    binding.buyPlantTypeTv.text = callResponse[0].plantCategory
                    binding.buyPlantStateTv.text = "State : $stateText"



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

        binding.buyDeadlineSpinner.adapter = ArrayAdapter.createFromResource(
            mainActivity, R.array.sellDeadlineDate, R.layout.spinner_item)
        binding.buyDeadlineSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                when (position){
                    0 ->{ //1일
                        df.format(today.time)
                        today.add(Calendar.DATE, 1)
                    }
                    1 ->{ //3일
                        df.format(today.time)
                        today.add(Calendar.DATE, 3)
                    }
                    2 ->{ //7일
                        df.format(today.time)
                        today.add(Calendar.DATE, 7)
                    }
                    3 ->{ //30일
                        df.format(today.time)
                        today.add(Calendar.DATE, 30)
                    }
                    4 ->{ //60일
                        df.format(today.time)
                        today.add(Calendar.DATE, 60)
                    }
                    else ->{
                        df.format(today.time)
                        today.add(Calendar.DATE, 1)
                    }
                }
            }
        }

        // api 연결 이후 확인 필요
        var strAmount = ""
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(s.toString()) && !s.toString().equals(strAmount)) {
                    strAmount = makeStringComma(s.toString().replace(",", ""))!!
                    binding.buyHopePriceEt.setText(strAmount)
                    val editable: Editable = binding.buyHopePriceEt.text
                    Selection.setSelection(editable, strAmount.length)
                    binding.buyMileageTv.text = binding.buyHopePriceEt.text.toString()
                }
            }
        }

        binding.buyHopePriceEt.addTextChangedListener(textWatcher)

        // 예측거래가 넣어야함
        binding.buyPredictionPriceTv.text = "예측 불가" + " 원"

        binding.buyNowBuyPriceTv.visibility = View.INVISIBLE
        binding.buyDeadlineTv.visibility = View.VISIBLE
        binding.buyDeadlineSpinner.visibility = View.VISIBLE
        binding.buyHopePriceEt.hint = "희망가 입력"

        binding.switchOnOff.isChecked=true
        binding.switchOnOff.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                // 구매 입찰
                binding.buyTypeTv.text = "구매 희망가"
                binding.buyNowBuyPriceTv.visibility = View.INVISIBLE
                binding.buyDeadlineTv.visibility = View.VISIBLE
                binding.buyDeadlineSpinner.visibility = View.VISIBLE
                binding.buyHopePriceEt.hint = "희망가 입력"
                binding.buyHopePriceEt.setText("0")

                binding.buyBtn.setOnClickListener {
                    Log.d("클릭 이벤트 감지", "클릭 이벤트 감지")
                    service.userInfo().enqueue(object : Callback<UserMeResponse> {
                        override fun onResponse(
                            call: Call<UserMeResponse>,
                            response: Response<UserMeResponse>
                        ) {
                            if (response.isSuccessful) {
                                val callResponse = response.body()!!
                                Log.d("GoooodInfoTest", callResponse.toString())
                                Log.d("GoooodInfoTest", response.headers().toString())

                                val priceEdit = binding.buyHopePriceEt.text.toString().replace(",", "")
                                val buyHopeData = PostBidRequest(
                                    plantNumberResult,
                                    stateResult!!,
                                    0,
                                    priceEdit.toInt(),
                                    0,
                                    "",
                                    today.toString(),
                                    0,
                                    "",
                                    "스파티필룸", // 식물 이름 bundle로 받아야 함
                                    0,
                                    priceResult!!,
                                    0,
                                    0,
                                    callResponse.userNo,
                                    0
                                )

                                // 1. @POST /product를 통해 구매 입찰 추가
                                service.postBidList(buyHopeData).enqueue(object : Callback<String> {
                                    override fun onResponse(
                                        call: Call<String>,
                                        response: Response<String>
                                    ) {
                                        if (response.isSuccessful) {
                                            val callResponse = response.body()!!
                                            Log.d("GoooodBidTest", callResponse)
                                            Log.d("GoooodBidTest", response.headers().toString())

                                        } else {
                                            Log.d(
                                                "BaaaadBidTest",
                                                NetworkUtil.getErrorResponse(response.errorBody()!!)
                                                    .toString()
                                            )
                                            Log.d("BaaaadBidTest", response.toString())

                                        }
                                    }

                                    override fun onFailure(call: Call<String>, t: Throwable) {
                                        Log.d("Real BaaaadBidTest", "onResponse 대실패")
                                    }


                                })

                                // 2. @POST /notification을 통해 알림 추가
                                val NotificationData = UserNotificationPostRequest(
                                    callResponse.userMileage,
                                    callResponse.userNo,
                                    0,
                                    "입찰 완료",
                                    null,
                                    0,
                                    plantNumberResult
                                )
                                service.userNotificationPost(NotificationData)
                                    .enqueue(object : Callback<String> {
                                        override fun onResponse(
                                            call: Call<String>,
                                            response: Response<String>
                                        ) {
                                            if (response.isSuccessful) {
                                                val callResponse = response.body()!!
                                                Log.d("GoooodNoti", callResponse)
                                                Log.d("GoooodNoti", response.headers().toString())

                                                // bundle
                                            } else {
                                                Log.d(
                                                    "BaaaadNoti",
                                                    NetworkUtil.getErrorResponse(response.errorBody()!!)
                                                        .toString()
                                                )
                                                Log.d("BaaaadNoti", response.toString())

                                            }
                                        }

                                        override fun onFailure(call: Call<String>, t: Throwable) {
                                            Log.d("Real BaaaadNoti", "onResponse 대실패")
                                        }


                                    })


                            } else {
                                Log.d(
                                    "BaaaadInfoTest",
                                    NetworkUtil.getErrorResponse(response.errorBody()!!).toString()
                                )
                                Log.d("BaaaadInfoTest", response.toString())

                            }
                        }

                        override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                            Log.d("Real BaaaadInfoTest", "onResponse 대실패")
                        }

                    })
                }
            }
            else{
                // 즉시 구매
                binding.buyNowBuyPriceTv.visibility = View.VISIBLE
                binding.buyTypeTv.text = "즉시 구매가"
                binding.buyMileageTv.text = decimal.format(priceResult)
                // 가장 낮은 가격으로
                binding.buyNowBuyPriceTv.text = decimal.format(priceResult)
                binding.buyDeadlineTv.visibility = View.INVISIBLE
                binding.buyDeadlineSpinner.visibility = View.INVISIBLE
                binding.buyHopePriceEt.hint = " "
                binding.buyHopePriceEt.text.clear()
/*
                binding.buyBtn.setOnClickListener {
                    /*
                    * 1. @DELETE /product를 통해 삭제 절차
                    * 2. @PUT /user/me를 통해 마일리지 수정
                    * 3. @PUT /user/my/bid를 통해 유저 입찰 내역 수정
                    * 4. @POST /user/notification을 통해 유저 알림 추가
                    * */
                }*/
            }
        }

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    protected fun makeStringComma(str: String): String? {    // 천단위 콤마설정.
        if (str.length == 0) {
            return ""
        }
        val value = str.toLong()
        val format = DecimalFormat("###,###")
        return format.format(value)
    }
}