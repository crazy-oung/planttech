package com.example.plant.Fragment

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.BoardAllListAdapter
import com.example.plant.adapter.PlantInfoAdapter
import com.example.plant.adapter.PlantInfoGraphAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentPlantInfoBinding
import com.example.plant.model.Board
import com.example.plant.model.GetUserPlantSensorListRequest
import com.example.plant.model.PlantImageGetResponse
import com.example.plant.model.PlantListResponse
import com.example.plant.model.PlantMoreInfo
import com.example.plant.model.UserMeResponse
import io.socket.client.IO
import io.socket.client.Socket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random


class PlantInfoFragment : Fragment() {
    lateinit var plantInfoRecyclerView : RecyclerView
    lateinit var plantInfoAdapter: PlantInfoAdapter
    lateinit var plantInfoGraphRecyclerView : RecyclerView
    lateinit var plantInfoGraphAdapter: PlantInfoGraphAdapter
    lateinit var mainActivity: MainActivity
    private lateinit var socket: Socket
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantInfoBinding.inflate(inflater, container, false)
        val service = ApiClient.getApiInterface()
        val plantNickname = arguments?.getString("plantNickname")
        val plantNo = arguments?.getInt("plantNo")
        val plantState = arguments?.getString("plantState")
        val plantWHNo = arguments?.getInt("plantWHNo")
        val random = Random()

        binding.plantInfoCameraIv.setImageResource(R.drawable.image_redy)
        service.plantImage(0,10, null, plantWHNo!!, null).enqueue( object : Callback<PlantImageGetResponse> {
            override fun onResponse(
                call: Call<PlantImageGetResponse>,
                response: Response<PlantImageGetResponse>
            ) {
                if(response.isSuccessful){
                    val callPhotoResponse = response.body()!!
                    Log.d("Gooood", callPhotoResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    binding.plantInfoStateTv.text = callPhotoResponse[0].plantColorGrade

                    when(callPhotoResponse[0].plantColorGrade){
                        "Very Good" -> {
                            if ( random.nextInt(2) == 0){

                            } else {

                            }
                        }
                        "Good" -> {
                            if ( random.nextInt(2) == 0){

                            } else {

                            }
                        }
                        "Soso" -> {
                            if ( random.nextInt(2) == 0){
                                binding.plantInfoStateExpTv.text = "음... 그저 그렇네요"
                            } else {
                                binding.plantInfoStateExpTv.text = "나쁘지도 좋지도 않아요"
                            }
                        }
                        "Bad" -> {
                            if ( random.nextInt(2) == 0){
                                binding.plantInfoStateExpTv.text = "살짝 기분이 나빠요"
                            } else {
                                binding.plantInfoStateExpTv.text = "몸상태가 안좋은 것 같아요"
                            }
                        }
                        "Very Bad" -> {
                            if ( random.nextInt(2) == 0){

                            } else {

                            }
                        }
                    }

                    service.getPlantSensorList(plantWHNo, 30).enqueue( object : Callback<GetUserPlantSensorListRequest>{
                        override fun onResponse(
                            call: Call<GetUserPlantSensorListRequest>,
                            response: Response<GetUserPlantSensorListRequest>
                        ) {
                            if(response.isSuccessful){
                                val callSensorResponse = response.body()!!
                                Log.d("Gooood", callSensorResponse.toString())
                                Log.d("Gooood", response.headers().toString())

                                // 각 센서에 따른 대화값 출력
                                if( callSensorResponse[0].humi!! > 65.0)
                                    binding.plantInfoStateExpTv.text = "조금 습한것 같아요.."




                            } else {
                                Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                                Log.d("Baaaad", response.toString())

                            }
                        }

                        override fun onFailure(call: Call<GetUserPlantSensorListRequest>, t: Throwable) {
                            Log.d("Real Baaaad", "onResponse 대실패")
                        }

                    })

                    val test = Base64.decode(callPhotoResponse[0].plantColorPic, Base64.DEFAULT)
                    val decode = BitmapFactory.decodeByteArray(test, 0, test.size)

                    binding.plantInfoCameraStateTv.text = "색채 분석 상태 : " +callPhotoResponse[0].plantColorGrade
                    binding.plantInfoCameraIv.setImageBitmap(decode)
                    if( callPhotoResponse[0].plantColorPic == "string")
                        binding.plantInfoCameraIv.setImageResource(R.drawable.image_redy)

                } else {

                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())
                }
            }

            override fun onFailure(call: Call<PlantImageGetResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })

        binding.switchOnOff.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                socket.disconnect()
                binding.plantInfoCameraStateTv.visibility = View.VISIBLE

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

                            val test = Base64.decode(callPhotoResponse[0].plantColorPic, Base64.DEFAULT)
                            val decode = BitmapFactory.decodeByteArray(test, 0, test.size)
                            binding.plantInfoCameraIv.setImageResource(R.drawable.image_redy)
                            binding.plantInfoCameraStateTv.text = "색채 분석 상태 : " +callPhotoResponse[0].plantColorGrade
                            Log.d("imageTest", callPhotoResponse[0].plantColorPic)
                            binding.plantInfoCameraIv.setImageBitmap(decode)

                        } else {
                            Log.d("BaaaadImage", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                            Log.d("BaaaadImage", response.toString())
                        }
                    }

                    override fun onFailure(call: Call<PlantImageGetResponse>, t: Throwable) {
                        Log.d("Real Baaaad", "onResponse 대실패")
                    }

                })
            } else {
                // 실시간 화면 불러와야함
                binding.plantInfoCameraIv.setImageResource(R.drawable.image_redy)
                binding.plantInfoCameraStateTv.visibility = View.INVISIBLE
                socket = IO.socket("http://220.68.82.79:4000")
                socket.connect()
                socket.on("user1") { args ->
                    val arg = args[0].toString()
                    val decodedBytes = Base64.decode(arg, Base64.DEFAULT)

                    mainActivity.runOnUiThread {
                        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
                        binding.plantInfoCameraIv.setImageBitmap(bitmap)
                    }


                }
            }
        }


        service.plantList(0, 10, plantNo).enqueue( object : Callback<PlantListResponse> {
            override fun onResponse(
                call: Call<PlantListResponse>,
                response: Response<PlantListResponse>
            ) {
                if(response.isSuccessful){
                    val callPlantResponse = response.body()!!
                    Log.d("Gooood", callPlantResponse.toString())
                    Log.d("Gooood", response.headers().toString())

                    binding.plantInfoNameTv.text = plantNickname
                    binding.plantInfoSubNameTv.text = callPlantResponse[0].plantKoreanName

                    val dataFruit = mutableListOf(
                        PlantMoreInfo("품종", callPlantResponse[0].plantKoreanName),
                        PlantMoreInfo("종류", callPlantResponse[0].plantCategory),
                        PlantMoreInfo("자생지", callPlantResponse[0].plantOrigin),
                        PlantMoreInfo("팁", callPlantResponse[0].plantCultivateTip)
                    )

                    plantInfoAdapter = context?.let { PlantInfoAdapter(dataFruit) }!!
                    binding.plantInfoMoreInfoRcv.adapter = plantInfoAdapter

                    plantInfoRecyclerView = binding.plantInfoMoreInfoRcv
                    plantInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    plantInfoRecyclerView.adapter = PlantInfoAdapter(dataFruit)
                    binding.plantInfoMoreInfoRcv.setHasFixedSize(true)

                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }

            override fun onFailure(call: Call<PlantListResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })


        binding.plantInfoExpectationPriceBtn.setOnClickListener {
            /*
            val bundle = Bundle()
            bundle.putInt("plantWHNo", plantWHNo)
            val expectationPriceBottomViewFragment = ExpectationPriceBottomViewFragment()
            expectationPriceBottomViewFragment.arguments = bundle
            parentFragmentManager.beginTransaction().
            add(R.id.fragmentContainer, ExpectationPriceBottomViewFragment()).
            commit()
*/
            val priceBottomSheet = ExpectationPriceBottomViewFragment()
            priceBottomSheet.show(mainActivity.supportFragmentManager, priceBottomSheet.tag)
        }

        val expData = mutableListOf(mutableListOf("0", "1", "17.8", "54.9", "426.1", "17.8", "spring", "4"), mutableListOf("0", "2", "14.4", "57.1", "527.7", "14.4", "spring", "4"), mutableListOf("0", "3", "14.0", "58.4", "526.0", "14.0", "spring", "4"), mutableListOf("0", "4", "14.6", "58.0", "581.1", "14.6", "spring", "4"), mutableListOf("0", "5", "14.6", "58.2", "536.3", "14.6", "spring", "4"), mutableListOf("0", "6", "14.2", "58.4", "534.9", "14.2", "spring", "4"), mutableListOf("0", "7", "14.6", "57.8", "532.4", "14.6", "spring", "4"), mutableListOf("0", "8", "14.9", "58.1", "541.4", "14.9", "spring", "4"), mutableListOf("0", "9", "14.7", "57.3", "534.6", "14.7", "spring", "4"), mutableListOf("0", "10", "14.6", "56.7", "544.3", "14.6", "spring", "4"), mutableListOf("0", "11", "14.6", "57.3", "535.4", "14.6", "spring", "4"), mutableListOf("0", "12", "14.3", "57.5", "526.4", "14.3", "spring", "4"), mutableListOf("0", "13", "14.8", "57.9", "547.6", "14.8", "spring", "4"), mutableListOf("0", "14", "14.9", "58.2", "544.0", "14.9", "spring", "4"), mutableListOf("0", "15", "14.8", "58.0", "533.4", "14.8", "spring", "4"), mutableListOf("0", "16", "14.9", "58.0", "537.5", "14.9", "spring", "4"), mutableListOf("0", "17", "14.9", "57.7", "537.6", "14.9", "spring", "4"), mutableListOf("0", "18", "15.0", "57.5", "540.1", "15.0", "spring", "4"), mutableListOf("0", "19", "15.0", "57.7", "533.9", "15.0", "spring", "4"), mutableListOf("0", "20", "15.0", "57.6", "518.1", "15.0", "spring", "4"), mutableListOf("0", "21", "15.2", "57.4", "513.3", "15.2", "spring", "4"), mutableListOf("0", "22", "15.5", "57.1", "507.4", "15.5", "spring", "4"), mutableListOf("0", "23", "15.6", "57.0", "501.2", "15.6", "spring", "4"), mutableListOf("0", "24", "15.8", "56.8", "500.7", "15.8", "spring", "4"), mutableListOf("0", "25", "16.2", "56.3", "500.3", "16.2", "spring", "4"), mutableListOf("0", "26", "16.2", "56.1", "493.1", "16.2", "spring", "4"), mutableListOf("0", "27", "16.4", "56.0", "493.3", "16.4", "spring", "4"), mutableListOf("0", "28", "16.3", "56.2", "491.9", "16.3", "spring", "4"), mutableListOf("0", "29", "16.5", "56.3", "489.0", "16.5", "spring", "4"), mutableListOf("0", "30", "16.7", "56.3", "491.3", "16.7", "spring", "4"), mutableListOf("0", "31", "16.6", "56.3", "494.1", "16.6", "spring", "4"), mutableListOf("0", "32", "16.9", "56.4", "493.7", "16.9", "spring", "4"), mutableListOf("0", "33", "17.1", "56.6", "499.9", "17.1", "spring", "4"), mutableListOf("0", "34", "17.3", "56.6", "503.6", "17.3", "spring", "4"), mutableListOf("0", "35", "17.2", "56.7", "508.9", "17.2", "spring", "4"), mutableListOf("0", "36", "17.2", "56.7", "507.7", "17.2", "spring", "4"), mutableListOf("0", "37", "17.1", "56.8", "506.1", "17.1", "spring", "4"), mutableListOf("0", "38", "17.2", "56.9", "500.9", "17.2", "spring", "4"), mutableListOf("0", "39", "17.2", "56.8", "503.1", "17.2", "spring", "4"), mutableListOf("0", "40", "17.2", "56.9", "502.6", "17.2", "spring", "4"), mutableListOf("0", "41", "17.2", "56.9", "499.6", "17.2", "spring", "4"), mutableListOf("0", "42", "17.2", "56.9", "497.8", "17.2", "spring", "4"), mutableListOf("0", "43", "17.3", "57.0", "497.8", "17.3", "spring", "4"), mutableListOf("0", "44", "17.4", "57.0", "496.3", "17.4", "spring", "4"), mutableListOf("0", "45", "17.4", "57.0", "497.7", "17.4", "spring", "4"), mutableListOf("0", "46", "17.5", "56.9", "500.0", "17.5", "spring", "4"), mutableListOf("0", "47", "17.4", "56.8", "497.0", "17.4", "spring", "4"), mutableListOf("0", "48", "17.5", "56.9", "495.2", "17.5", "spring", "4"), mutableListOf("0", "49", "17.5", "56.8", "492.5", "17.5", "spring", "4"), mutableListOf("0", "50", "17.4", "56.8", "491.6", "17.4", "spring", "4"), mutableListOf("0", "51", "17.5", "56.8", "494.2", "17.5", "spring", "4"), mutableListOf("0", "52", "17.5", "56.7", "496.2", "17.5", "spring", "4"), mutableListOf("0", "53", "17.5", "56.7", "492.4", "17.5", "spring", "4"), mutableListOf("0", "54", "17.4", "56.7", "497.8", "17.4", "spring", "4"), mutableListOf("0", "55", "17.3", "56.7", "493.5", "17.3", "spring", "4"), mutableListOf("0", "56", "17.3", "56.6", "494.3", "17.3", "spring", "4"), mutableListOf("0", "57", "17.3", "56.6", "497.4", "17.3", "spring", "4"), mutableListOf("0", "58", "17.3", "56.5", "498.3", "17.3", "spring", "4"), mutableListOf("0", "59", "17.3", "56.4", "498.3", "17.3", "spring", "4"), mutableListOf("0", "60", "17.4", "56.4", "496.7", "17.4", "spring", "4"), mutableListOf("0", "61", "17.4", "56.5", "497.8", "17.4", "spring", "4"), mutableListOf("0", "62", "17.3", "56.5", "499.2", "17.3", "spring", "4"), mutableListOf("0", "63", "17.3", "56.4", "500.1", "17.3", "spring", "4"), mutableListOf("0", "64", "17.3", "56.5", "499.5", "17.3", "spring", "4"), mutableListOf("0", "65", "17.4", "56.4", "502.9", "17.4", "spring", "4"), mutableListOf("0", "66", "17.4", "56.3", "501.6", "17.4", "spring", "4"), mutableListOf("0", "67", "17.4", "56.2", "503.0", "17.4", "spring", "4"), mutableListOf("0", "68", "17.4", "56.2", "503.3", "17.4", "spring", "4"), mutableListOf("0", "69", "17.3", "56.4", "505.4", "17.3", "spring", "4"), mutableListOf("0", "70", "17.3", "56.4", "507.0", "17.3", "spring", "4"), mutableListOf("0", "71", "17.3", "56.4", "503.9", "17.3", "spring", "4"), mutableListOf("0", "72", "17.2", "56.5", "504.2", "17.2", "spring", "4"), mutableListOf("0", "73", "17.3", "56.4", "502.6", "17.3", "spring", "4"), mutableListOf("0", "74", "17.3", "56.4", "503.5", "17.3", "spring", "4"), mutableListOf("0", "75", "17.3", "56.3", "504.3", "17.3", "spring", "4"), mutableListOf("0", "76", "17.2", "56.4", "504.1", "17.2", "spring", "4"), mutableListOf("0", "77", "17.2", "56.4", "504.4", "17.2", "spring", "4"), mutableListOf("0", "78", "17.2", "56.5", "503.4", "17.2", "spring", "4"), mutableListOf("0", "79", "17.2", "56.5", "500.6", "17.2", "spring", "4"), mutableListOf("0", "80", "17.2", "56.4", "503.6", "17.2", "spring", "4"), mutableListOf("0", "81", "17.2", "56.5", "501.6", "17.2", "spring", "4"), mutableListOf("0", "82", "17.2", "56.4", "504.1", "17.2", "spring", "4"), mutableListOf("0", "83", "17.2", "56.4", "503.7", "17.2", "spring", "4"), mutableListOf("0", "84", "17.1", "56.5", "507.0", "17.1", "spring", "4"), mutableListOf("0", "85", "17.1", "56.4", "506.2", "17.1", "spring", "4"), mutableListOf("0", "86", "17.1", "56.4", "507.1", "17.1", "spring", "4"), mutableListOf("0", "87", "17.1", "56.4", "506.0", "17.1", "spring", "4"), mutableListOf("0", "88", "17.1", "56.3", "509.8", "17.1", "spring", "4"), mutableListOf("0", "89", "17.0", "56.3", "508.1", "17.0", "winter", "4"), mutableListOf("0", "90", "17.0", "56.3", "509.2", "17.0", "winter", "4"), mutableListOf("0", "91", "16.9", "56.3", "509.7", "16.9", "winter", "4"), mutableListOf("0", "92", "16.9", "56.3", "508.3", "16.9", "winter", "4"), mutableListOf("0", "93", "16.9", "56.3", "506.7", "16.9", "winter", "4"), mutableListOf("0", "94", "16.8", "56.4", "504.4", "16.8", "winter", "4"), mutableListOf("0", "95", "16.8", "56.5", "506.8", "16.8", "winter", "4"), mutableListOf("0", "96", "16.9", "56.5", "505.9", "16.9", "winter", "4"), mutableListOf("0", "97", "16.8", "56.5", "505.0", "16.8", "winter", "4"), mutableListOf("0", "98", "16.8", "56.5", "506.2", "16.8", "winter", "4"), mutableListOf("0", "99", "16.8", "56.5", "504.4", "16.8", "winter", "4"), mutableListOf("0", "100", "16.7", "56.5", "504.4", "16.7", "winter", "4"), mutableListOf("0", "101", "16.7", "56.5", "504.5", "16.7", "winter", "4"), mutableListOf("0", "102", "16.7", "56.5", "504.9", "16.7", "winter", "4"), mutableListOf("0", "103", "16.7", "56.5", "504.8", "16.7", "winter", "4"), mutableListOf("0", "104", "16.7", "56.5", "504.7", "16.7", "winter", "4"), mutableListOf("0", "105", "16.7", "56.5", "503.4", "16.7", "winter", "4"), mutableListOf("0", "106", "16.6", "56.5", "502.9", "16.6", "winter", "4"), mutableListOf("0", "107", "16.6", "56.5", "504.3", "16.6", "winter", "4"), mutableListOf("0", "108", "16.6", "56.4", "503.0", "16.6", "winter", "4"), mutableListOf("0", "109", "16.6", "56.4", "503.8", "16.6", "winter", "4"), mutableListOf("0", "110", "16.5", "56.4", "503.8", "16.5", "winter", "4"), mutableListOf("0", "111", "16.5", "56.3", "502.7", "16.5", "winter", "4"), mutableListOf("0", "112", "16.5", "56.4", "503.2", "16.5", "winter", "4"), mutableListOf("0", "113", "16.4", "56.3", "503.8", "16.4", "winter", "4"), mutableListOf("0", "114", "16.4", "56.3", "504.1", "16.4", "winter", "4"), mutableListOf("0", "115", "16.4", "56.3", "504.0", "16.4", "winter", "4"), mutableListOf("0", "116", "16.4", "56.3", "503.4", "16.4", "winter", "4"), mutableListOf("0", "117", "16.4", "56.3", "503.3", "16.4", "winter", "4"), mutableListOf("0", "118", "16.4", "56.3", "504.3", "16.4", "winter", "4"), mutableListOf("0", "119", "16.4", "56.3", "506.0", "16.4", "winter", "4"), mutableListOf("0", "120", "16.4", "56.3", "505.4", "16.4", "winter", "4"), mutableListOf("0", "121", "16.4", "56.3", "505.1", "16.4", "winter", "4"), mutableListOf("0", "122", "16.5", "56.2", "505.6", "16.5", "winter", "4"), mutableListOf("0", "123", "16.5", "56.2", "507.2", "16.5", "winter", "4"), mutableListOf("0", "124", "16.5", "56.2", "506.6", "16.5", "winter", "4"), mutableListOf("0", "125", "16.5", "56.2", "505.4", "16.5", "winter", "4"), mutableListOf("0", "126", "16.5", "56.2", "505.4", "16.5", "winter", "4"), mutableListOf("0", "127", "16.5", "56.2", "505.7", "16.5", "winter", "4"), mutableListOf("0", "128", "16.5", "56.2", "506.1", "16.5", "winter", "4"), mutableListOf("0", "129", "16.5", "56.2", "506.8", "16.5", "winter", "4"), mutableListOf("0", "130", "16.5", "56.2", "508.2", "16.5", "winter", "4"), mutableListOf("0", "131", "16.5", "56.2", "507.9", "16.5", "winter", "4"), mutableListOf("0", "132", "16.6", "56.2", "508.8", "16.6", "winter", "4"), mutableListOf("0", "133", "16.6", "56.2", "509.4", "16.6", "winter", "4"), mutableListOf("0", "134", "16.6", "56.2", "510.8", "16.6", "winter", "4"), mutableListOf("0", "135", "16.6", "56.2", "512.0", "16.6", "winter", "4"), mutableListOf("0", "136", "16.6", "56.2", "512.2", "16.6", "winter", "4"), mutableListOf("0", "137", "16.6", "56.2", "513.7", "16.6", "winter", "4"), mutableListOf("0", "138", "16.6", "56.2", "514.4", "16.6", "winter", "4"), mutableListOf("0", "139", "16.6", "56.2", "514.7", "16.6", "winter", "4"), mutableListOf("0", "140", "16.6", "56.2", "513.7", "16.6", "winter", "4"), mutableListOf("0", "141", "16.6", "56.2", "513.8", "16.6", "winter", "4"), mutableListOf("0", "142", "16.6", "56.3", "513.1", "16.6", "winter", "4"), mutableListOf("0", "143", "16.7", "56.3", "513.3", "16.7", "winter", "4"), mutableListOf("0", "144", "16.6", "56.3", "513.5", "16.6", "winter", "4"), mutableListOf("0", "145", "16.7", "56.3", "513.9", "16.7", "winter", "4"), mutableListOf("0", "146", "16.7", "56.2", "514.8", "16.7", "winter", "4"), mutableListOf("0", "147", "16.7", "56.2", "514.6", "16.7", "winter", "4"), mutableListOf("0", "148", "16.7", "56.2", "513.7", "16.7", "winter", "4"), mutableListOf("0", "149", "16.7", "56.2", "513.8", "16.7", "winter", "4"), mutableListOf("0", "150", "16.7", "56.2", "513.1", "16.7", "winter", "4"), mutableListOf("0", "151", "16.7", "56.2", "512.1", "16.7", "winter", "4"), mutableListOf("0", "152", "16.7", "56.2", "511.7", "16.7", "winter", "4"), mutableListOf("0", "153", "16.7", "56.2", "511.4", "16.7", "winter", "4"), mutableListOf("0", "154", "16.7", "56.2", "512.2", "16.7", "winter", "4"), mutableListOf("0", "155", "16.7", "56.2", "513.0", "16.7", "winter", "4"), mutableListOf("0", "156", "16.7", "56.2", "512.3", "16.7", "winter", "4"), mutableListOf("0", "157", "16.7", "56.2", "513.0", "16.7", "winter", "4"), mutableListOf("0", "158", "16.7", "56.2", "513.2", "16.7", "winter", "4"), mutableListOf("0", "159", "16.7", "56.2", "513.8", "16.7", "winter", "4"), mutableListOf("0", "160", "16.7", "56.1", "513.1", "16.7", "winter", "4"), mutableListOf("0", "161", "16.7", "56.1", "514.0", "16.7", "winter", "4"), mutableListOf("0", "162", "16.7", "56.1", "513.7", "16.7", "winter", "4"), mutableListOf("0", "163", "16.7", "56.1", "514.0", "16.7", "winter", "4"), mutableListOf("0", "164", "16.7", "56.0", "513.2", "16.7", "winter", "4"), mutableListOf("0", "165", "16.7", "56.1", "512.9", "16.7", "winter", "4"), mutableListOf("0", "166", "16.7", "56.0", "512.9", "16.7", "winter", "4"), mutableListOf("0", "167", "16.7", "56.0", "512.8", "16.7", "winter", "4"), mutableListOf("0", "168", "16.7", "56.0", "512.0", "16.7", "winter", "4"), mutableListOf("0", "169", "16.7", "56.1", "511.6", "16.7", "winter", "4"), mutableListOf("0", "170", "16.7", "56.1", "510.6", "16.7", "winter", "4"), mutableListOf("0", "171", "16.7", "56.0", "511.3", "16.7", "winter", "4"), mutableListOf("0", "172", "16.7", "56.1", "511.0", "16.7", "winter", "4"), mutableListOf("0", "173", "16.7", "56.0", "510.7", "16.7", "winter", "4"), mutableListOf("0", "174", "16.7", "56.0", "511.2", "16.7", "winter", "4"), mutableListOf("0", "175", "16.7", "56.0", "510.8", "16.7", "winter", "4"), mutableListOf("0", "176", "16.7", "56.0", "510.9", "16.7", "winter", "4"), mutableListOf("0", "177", "16.7", "56.0", "511.2", "16.7", "winter", "4"), mutableListOf("0", "178", "16.7", "56.0", "511.0", "16.7", "winter", "4"), mutableListOf("0", "179", "16.7", "56.0", "511.9", "16.7", "fall", "4"), mutableListOf("0", "180", "16.7", "56.0", "512.1", "16.7", "fall", "4"), mutableListOf("0", "181", "16.7", "55.9", "512.0", "16.7", "fall", "4"), mutableListOf("0", "182", "16.7", "55.9", "512.2", "16.7", "fall", "4"), mutableListOf("0", "183", "16.7", "55.9", "512.6", "16.7", "fall", "4"), mutableListOf("0", "184", "16.7", "55.9", "512.2", "16.7", "fall", "4"), mutableListOf("0", "185", "16.7", "55.9", "512.2", "16.7", "fall", "4"), mutableListOf("0", "186", "16.7", "55.9", "512.5", "16.7", "fall", "4"), mutableListOf("0", "187", "16.7", "55.9", "512.2", "16.7", "fall", "4"), mutableListOf("0", "188", "16.7", "55.9", "512.2", "16.7", "fall", "4"), mutableListOf("0", "189", "16.7", "55.9", "511.7", "16.7", "fall", "4"), mutableListOf("0", "190", "16.7", "55.9", "511.1", "16.7", "fall", "4"), mutableListOf("0", "191", "16.7", "55.9", "511.1", "16.7", "fall", "4"), mutableListOf("0", "192", "16.7", "55.9", "511.3", "16.7", "fall", "4"), mutableListOf("0", "193", "16.7", "55.9", "512.1", "16.7", "fall", "4"), mutableListOf("0", "194", "16.7", "55.9", "511.3", "16.7", "fall", "4"), mutableListOf("0", "195", "16.7", "55.9", "512.0", "16.7", "fall", "4"), mutableListOf("0", "196", "16.7", "55.9", "511.9", "16.7", "fall", "4"), mutableListOf("0", "197", "16.7", "55.9", "512.9", "16.7", "fall", "4"), mutableListOf("0", "198", "16.7", "55.9", "512.3", "16.7", "fall", "4"), mutableListOf("0", "199", "16.7", "55.9", "511.9", "16.7", "fall", "4"), mutableListOf("0", "200", "16.8", "55.9", "511.7", "16.8", "fall", "4"), mutableListOf("0", "201", "16.8", "55.9", "511.6", "16.8", "fall", "4"), mutableListOf("0", "202", "16.8", "55.9", "511.7", "16.8", "fall", "4"), mutableListOf("0", "203", "16.8", "55.9", "511.4", "16.8", "fall", "4"), mutableListOf("0", "204", "16.8", "55.9", "511.2", "16.8", "fall", "4"), mutableListOf("0", "205", "16.8", "55.9", "511.7", "16.8", "fall", "4"), mutableListOf("0", "206", "16.8", "55.9", "511.4", "16.8", "fall", "4"), mutableListOf("0", "207", "16.8", "55.9", "511.0", "16.8", "fall", "4"), mutableListOf("0", "208", "16.8", "55.9", "510.6", "16.8", "fall", "4"), mutableListOf("0", "209", "16.8", "55.9", "510.9", "16.8", "fall", "4"), mutableListOf("0", "210", "16.8", "55.9", "510.9", "16.8", "fall", "4"), mutableListOf("0", "211", "16.8", "55.9", "510.8", "16.8", "fall", "4"), mutableListOf("0", "212", "16.8", "55.9", "511.1", "16.8", "fall", "4"), mutableListOf("0", "213", "16.8", "55.9", "511.3", "16.8", "fall", "4"), mutableListOf("0", "214", "16.8", "55.8", "511.8", "16.8", "fall", "4"), mutableListOf("0", "215", "16.8", "55.8", "511.3", "16.8", "fall", "4"), mutableListOf("0", "216", "16.8", "55.8", "510.9", "16.8", "fall", "4"), mutableListOf("0", "217", "16.8", "55.8", "510.9", "16.8", "fall", "4"), mutableListOf("0", "218", "16.9", "55.8", "510.4", "16.9", "fall", "4"), mutableListOf("0", "219", "16.8", "55.8", "510.0", "16.8", "fall", "4"), mutableListOf("0", "220", "16.9", "55.8", "509.4", "16.9", "fall", "4"), mutableListOf("0", "221", "16.9", "55.9", "509.1", "16.9", "fall", "4"), mutableListOf("0", "222", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "223", "16.9", "55.9", "508.6", "16.9", "fall", "4"), mutableListOf("0", "224", "16.9", "55.9", "509.0", "16.9", "fall", "4"), mutableListOf("0", "225", "16.9", "55.8", "509.3", "16.9", "fall", "4"), mutableListOf("0", "226", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "227", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "228", "16.9", "55.8", "508.7", "16.9", "fall", "4"), mutableListOf("0", "229", "16.9", "55.8", "508.9", "16.9", "fall", "4"), mutableListOf("0", "230", "16.9", "55.8", "509.4", "16.9", "fall", "4"), mutableListOf("0", "231", "16.9", "55.8", "509.6", "16.9", "fall", "4"), mutableListOf("0", "232", "16.9", "55.8", "509.1", "16.9", "fall", "4"), mutableListOf("0", "233", "16.9", "55.8", "509.1", "16.9", "fall", "4"), mutableListOf("0", "234", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "235", "16.9", "55.8", "508.7", "16.9", "fall", "4"), mutableListOf("0", "236", "16.9", "55.8", "508.3", "16.9", "fall", "4"), mutableListOf("0", "237", "16.9", "55.8", "508.2", "16.9", "fall", "4"), mutableListOf("0", "238", "16.9", "55.8", "508.1", "16.9", "fall", "4"), mutableListOf("0", "239", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "240", "16.9", "55.8", "508.3", "16.9", "fall", "4"), mutableListOf("0", "241", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "242", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "243", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "244", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "245", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "246", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "247", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "248", "16.9", "55.8", "508.5", "16.9", "fall", "4"), mutableListOf("0", "249", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "250", "16.9", "55.8", "508.8", "16.9", "fall", "4"), mutableListOf("0", "251", "16.9", "55.8", "509.3", "16.9", "fall", "4"), mutableListOf("0", "252", "16.9", "55.8", "509.7", "16.9", "fall", "4"), mutableListOf("0", "253", "16.9", "55.8", "509.2", "16.9", "fall", "4"), mutableListOf("0", "254", "16.9", "55.8", "509.2", "16.9", "fall", "4"), mutableListOf("0", "255", "16.9", "55.8", "508.9", "16.9", "fall", "4"), mutableListOf("0", "256", "16.9", "55.8", "508.4", "16.9", "fall", "4"), mutableListOf("0", "257", "16.9", "55.8", "508.6", "16.9", "fall", "4"), mutableListOf("0", "258", "17.0", "55.8", "508.4", "17.0", "fall", "4"), mutableListOf("0", "259", "17.0", "55.8", "508.7", "17.0", "fall", "4"), mutableListOf("0", "260", "17.0", "55.8", "509.1", "17.0", "fall", "4"), mutableListOf("0", "261", "17.0", "55.8", "509.2", "17.0", "fall", "4"), mutableListOf("0", "262", "17.0", "55.8", "508.7", "17.0", "fall", "4"), mutableListOf("0", "263", "17.0", "55.8", "509.2", "17.0", "fall", "4"), mutableListOf("0", "264", "17.0", "55.8", "509.5", "17.0", "fall", "4"), mutableListOf("0", "265", "17.0", "55.8", "509.6", "17.0", "fall", "4"), mutableListOf("0", "266", "17.0", "55.8", "510.0", "17.0", "fall", "4"), mutableListOf("0", "267", "17.0", "55.8", "510.2", "17.0", "fall", "4"), mutableListOf("0", "268", "17.0", "55.8", "510.2", "17.0", "fall", "4"), mutableListOf("0", "269", "17.0", "55.8", "510.1", "17.0", "fall", "4"), mutableListOf("0", "270", "17.0", "55.8", "510.3", "17.0", "summer", "4"), mutableListOf("0", "271", "17.0", "55.8", "510.2", "17.0", "summer", "4"))

        val tempData = mutableListOf<Float>()
        for(i in expData){
            tempData.add(i[2].toFloat())
        }

        val humiData = mutableListOf<Float>()
        for(i in expData){
            tempData.add(i[3].toFloat())
        }

        val waterTempData = mutableListOf<Float>()
        for(i in expData){
            tempData.add(i[4].toFloat())
        }

        val lightData = mutableListOf<Float>()
        for(i in expData){
            tempData.add(i[5].toFloat())
        }

        val stateData = mutableListOf<Float>()
        for(i in expData){
            tempData.add(i[7].toFloat())
        }

        val data = mutableListOf<List<Float>>(
            tempData,
            humiData,
            waterTempData,
            lightData,
            stateData
        )



        Log.d("graphDataTest", tempData.toString())
        Log.d("graphDataTest", mutableListOf(1.0f, 1.2f).toString())

        plantInfoGraphAdapter = context?.let { PlantInfoGraphAdapter(data) }!!
        binding.plantInfoTempAndHumiRcv.adapter = plantInfoGraphAdapter

        plantInfoGraphRecyclerView = binding.plantInfoTempAndHumiRcv
        plantInfoGraphRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        plantInfoGraphRecyclerView.adapter = PlantInfoGraphAdapter(data)
        binding.plantInfoTempAndHumiRcv.setHasFixedSize(true)

        service.userInfo().enqueue( object : Callback<UserMeResponse>{
            override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                if(response.isSuccessful){
                    val callPhotoResponse = response.body()!!
                    Log.d("Gooood", callPhotoResponse.toString())
                    Log.d("Gooood", response.headers().toString())



                } else {
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }

            override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")
            }

        })

        var humiState = 0
        var pumpState =0

        binding.plantInfoHumiBtn.setOnClickListener {
            if(humiState == 0) {
                binding.plantInfoHumiBtn.text = "가습기 ON"
                toast("촉촉해 지겠네요!")
                humiState = 1
            } else {
                binding.plantInfoHumiBtn.text = "가습기 OFF"
                toast("보습이 끝났네요 다음에도 부탁드려요")
                humiState = 0
            }

        }

        binding.plantInfoWaterBtn.setOnClickListener {
            if(pumpState == 0) {
                binding.plantInfoWaterBtn.text = "워터 펌프 ON"
                toast("물을 줘서 정말 고마워요!")
                pumpState = 1
            } else {
                binding.plantInfoWaterBtn.text = "워터 펌프 OFF"
                toast("다음번에도 주실꺼죠?")
                pumpState = 0
            }

        }

        return binding.root
    }


override fun onAttach(context: Context) {
    super.onAttach(context)

    mainActivity = context as MainActivity
}
    fun toast(message: String){
        Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show()
    }
}