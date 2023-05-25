package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.api.RetrofitService
import com.example.plant.databinding.FragmentLoginBinding
import com.example.plant.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    private lateinit var loginData : LoginRequest
    private lateinit var data2 : LoginCall
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        val service = ApiClient.getApiInterface()

        binding.loginToolbar.title = "로그인"

        binding.loginBt.setOnClickListener {
            loginData = LoginRequest(binding.loginIdEt.text.toString(), binding.loginPasswordEt.text.toString())
            service.userLogin(loginData).enqueue(object : Callback<LoginCall> {
                override fun onResponse(call: Call<LoginCall>, response: Response<LoginCall>) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        data2 = response.body()!!
                        Log.d("Gooood", data2.toString())
                        Log.d("Gooood", response.headers().toString())
                        mainActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, HomeFragment())
                            .addToBackStack(null)
                            .commit()

                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                    }
                }


                override fun onFailure(call: Call<LoginCall>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }
            })

            // 로그인 이후 내 식물로 이동
        }


        binding.loginRegisterBt.setOnClickListener {
            //회원가입 화면으로 이동
            mainActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

}


