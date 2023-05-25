package com.example.plant

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.plant.Fragment.*
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.ActivityMainBinding
import com.example.plant.model.LoginCall
import com.example.plant.model.UserMeResponse
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val boardFragment = BoardFragment()
    private val infoFragment = InfoFragment()
    private val alamFragment = AlamFragment()
    private val cameraFragment = CameraFragment()
    private val plantAddFragment = PlantAddFragment()
    private val alamSettingFragment = AlamSettingFragment()
    private val loginFragment = LoginFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val service = ApiClient.getApiInterface()

        binding.bnvMain.setOnItemSelectedListener {
            service.userInfo().enqueue(object : Callback<UserMeResponse> {
                override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                    if (response.isSuccessful) {
                        // 정상적으로 통신이 성공된 경우
                        Log.d("Gooood", response.body()!!.toString())
                        when (it.itemId) {
                            //R.id.first -> replaceFragment(homeFragment)
                            //로컬 DB에 로그인 상태를 만들고 상태에 따라 프래그먼트 접근 가능

                            R.id.first -> replaceFragment(homeFragment)
                            R.id.second -> replaceFragment(boardFragment)
                            R.id.third -> replaceFragment(infoFragment)
                        }


                    } else {
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                        Log.d("Baaaad", response.toString())
                        toast("먼저 로그인을 진행해주세요")
                        when (it.itemId) {
                            //R.id.first -> replaceFragment(homeFragment)
                            //로컬 DB에 로그인 상태를 만들고 상태에 따라 프래그먼트 접근 가능

                            R.id.first -> replaceFragment(loginFragment)
                            R.id.second -> replaceFragment(boardFragment)
                            R.id.third -> replaceFragment(loginFragment)
                        }
                    }
                }


                override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                    Log.d("Real Baaaad", "onResponse 대실패")
                    toast("먼저 로그인을 진행해주세요")
                    when (it.itemId) {
                        //R.id.first -> replaceFragment(homeFragment)
                        //로컬 DB에 로그인 상태를 만들고 상태에 따라 프래그먼트 접근 가능

                        R.id.first -> replaceFragment(loginFragment)
                        R.id.second -> replaceFragment(boardFragment)
                        R.id.third -> replaceFragment(loginFragment)
                    }
                }
            })
            true
        }
        binding.bnvMain.selectedItemId = R.id.second



    }

    private fun replaceFragment(fragment: Fragment) {
        // 현 Activity 에 연결된 Fragment 관리하는 supportFragmentManager 를 통해 Fragment 전환
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

    fun changeFragment(index: Int){
        when(index){
            0 -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, homeFragment)
                    .addToBackStack(null)
                    .commit()

            }

            1 -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, alamFragment)
                    .addToBackStack(null)
                    .commit()
            }

            2 -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, alamSettingFragment)
                    .addToBackStack(null)
                    .commit()
            }

            3 -> {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, plantAddFragment)
                    .addToBackStack(null)
                    .commit()
            }

        }
    }
    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}