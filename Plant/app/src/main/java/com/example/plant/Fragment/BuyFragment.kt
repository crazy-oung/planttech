package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentBuyBinding
import com.example.plant.model.PlantListResponse
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
        val df: DateFormat = SimpleDateFormat("yyyy-MM-ddT00:00:00" + ".000Z")

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

                    binding.buyBtn.setOnClickListener {
                        /*
                        * 1. @DELETE /product를 통해 삭제 절차
                        * 2. @PUT /user/me를 통해 마일리지 수정
                        * 3. @PUT /user/my/bid를 통해 유저 입찰 내역 수정
                        * 4. @POST /user/notification을 통해 유저 알림 추가
                        * */
                    }

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


        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.buyMileageTv.text = decimal.format(priceResult)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.buyMileageTv.text = decimal.format(priceResult)
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
                binding.buyTypeTv.text = "구매 희망가"
                binding.buyNowBuyPriceTv.visibility = View.INVISIBLE
                binding.buyDeadlineTv.visibility = View.VISIBLE
                binding.buyDeadlineSpinner.visibility = View.VISIBLE
                binding.buyHopePriceEt.hint = "희망가 입력"
            }
            else{
                binding.buyNowBuyPriceTv.visibility = View.VISIBLE
                binding.buyTypeTv.text = "즉시 구매가"
                binding.buyMileageTv.text = decimal.format(priceResult)
                // 가장 낮은 가격으로
                binding.buyNowBuyPriceTv.text = decimal.format(priceResult)
                binding.buyDeadlineTv.visibility = View.INVISIBLE
                binding.buyDeadlineSpinner.visibility = View.INVISIBLE
                binding.buyHopePriceEt.hint = " "
            }
        }

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}