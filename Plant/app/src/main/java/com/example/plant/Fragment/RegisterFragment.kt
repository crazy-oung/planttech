package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.api.ApiClient.getApiInterface
import com.example.plant.api.NetworkUtil
import com.example.plant.api.RetrofitService
import com.example.plant.databinding.FragmentRegisterBinding
import com.example.plant.model.LoginResponseFail
import com.example.plant.model.UserRegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RegisterFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    private lateinit var registerRequest : UserRegisterRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)


        val service = getApiInterface()

        binding.registerToolbar.title = "회원가입"

        binding.registerBt.setOnClickListener {
            // 서비스 시작
            registerRequest = UserRegisterRequest(
                binding.registerBrthdateEt.text.toString(),
                binding.registerEamilEt.text.toString(),
                binding.registerIdEt.text.toString(),
                binding.registerNameEt.text.toString(),
                binding.registerNickNameEt.text.toString(),
                0,
                binding.registerPasswordEt.text.toString()
            )

            service.userRegister(registerRequest).enqueue(object : Callback<LoginResponseFail> {
                override fun onResponse(
                    call: Call<LoginResponseFail>,
                    response: Response<LoginResponseFail>
                ) {
                    if (response.isSuccessful) {
                        // 축하한다는 토스트 메세지 추가
                        mainActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, InfoFragment())
                            .addToBackStack(null)
                            .commit()


                        Log.d("Good", response.body().toString())
                        Log.d("Good", response.toString())
                    } else {
                        // 오류에 따른 실패 이유 토스트 메세지 추가
                        Log.d("Baaaad", response.toString())
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.errorBody().toString())
                    }

                }

                override fun onFailure(call: Call<LoginResponseFail>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                }
            })
        }

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }
}