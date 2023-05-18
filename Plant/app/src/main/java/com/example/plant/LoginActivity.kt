package com.example.plant

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.plant.Fragment.*
import com.example.plant.databinding.ActivityLoginBinding



class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", binding.inputId.toString())
        intent.putExtra("password", binding.inputPassword.toString())

        val registerIntent = Intent(this, RegisterActivity::class.java)
        binding.registerButton.setOnClickListener {
            startActivity(registerIntent)
        }
        binding.loginButton.setOnClickListener {
                        /*
            val retrofit = Retrofit.Builder().baseUrl("http://dayounghan.com/") //http://192.168.0.21:8080/ //http://dayounghan.com/
                .addConverterFactory(GsonConverterFactory.create()).build();
            val service = retrofit.create(RetrofitService::class.java);

            service.getPlant()?.enqueue(object : Callback<List<PlantInfo>>{
                override fun onResponse(call: Call<List<PlantInfo>>, response: Response<List<PlantInfo>>) {
                    if(response.isSuccessful){
                        // 정상적으로 통신이 성고된 경우
                        binding.info.text = response.body().toString()
                        Log.d("Gooood", "onResponse 성공: ");
                    }else{
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Log.d("Baaaad", "onResponse 실패")
                    }
                }
                override fun onFailure(call: Call<List<PlantInfo>>, t: Throwable) {
                    // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                    Log.d("Very Baaaad", "onFailure 에러: " + t.message.toString());
                }
            })


            binding.postButton.setOnClickListener{

                service.LEDRequest(0,binding.infoEdit2.text.toString().toInt()).enqueue(object : Callback<LedResponse>{
                    override fun onResponse(call: Call<LedResponse>, response: Response<LedResponse>) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성고된 경우
                            binding.response.text = response.body().toString()
                            Log.d("Gooood", "onResponse 성공: ")
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("Baaaad", "onResponse 실패" + response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<LedResponse>, t: Throwable) {
                        // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                        Log.d("Very Baaaad", "onFailure 에러: " + t.message.toString());
                    }

                })
            }*/
            startActivity(intent)
        }
    }
}