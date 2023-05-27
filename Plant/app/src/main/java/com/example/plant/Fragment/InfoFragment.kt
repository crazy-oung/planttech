package com.example.plant.Fragment

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.HomePagerFragmentStateAdapter
import com.example.plant.adapter.InfoAdapter
import com.example.plant.adapter.InfoPagerFragmentStateAdapter
import com.example.plant.api.ApiClient
import com.example.plant.api.NetworkUtil
import com.example.plant.databinding.FragmentInfoBinding
import com.example.plant.model.UserMeResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.socket.client.IO
import io.socket.client.Socket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URISyntaxException

class InfoFragment : Fragment(R.layout.fragment_info) {

    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout
    lateinit var infoData : UserMeResponse
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentInfoBinding.inflate(inflater, container, false)
        val pagerAdapter = InfoPagerFragmentStateAdapter(requireActivity())
        val service = ApiClient.getApiInterface()

        service.userInfo().enqueue(object : Callback<UserMeResponse> {
            override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    Log.d("Gooood", response.body()!!.toString())

                    val infoResponse = response.body()!!

                    binding.infoUserTv.text = infoResponse.userNickname
                    binding.infoPointGradeText.text = infoResponse.userMileage.toString()
                    binding.infoUserGradeText.text = infoResponse.userType


                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }


            override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")

            }
        })

        // api 연결 이후 확인 필요
        service.getUserMilageList().enqueue(object : Callback<UserMeResponse> {
            override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponse = response.body()!!
                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())



                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }


            override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")

            }
        })

        // api 연결 이후 확인 필요
        service.getUserBidList().enqueue(object : Callback<UserMeResponse> {
            override fun onResponse(call: Call<UserMeResponse>, response: Response<UserMeResponse>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    val callResponse = response.body()!!
                    Log.d("Gooood", callResponse.toString())
                    Log.d("Gooood", response.headers().toString())



                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("Baaaad", NetworkUtil.getErrorResponse(response.errorBody()!!).toString())
                    Log.d("Baaaad", response.toString())

                }
            }


            override fun onFailure(call: Call<UserMeResponse>, t: Throwable) {
                Log.d("Real Baaaad", "onResponse 대실패")

            }
        })

        viewPager = binding.infoPager
        tabLayout = binding.infoTabLayout

        pagerAdapter.addFragment(InfoTradeFragment())
        pagerAdapter.addFragment(InfoProfileFragment())

        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabList = listOf<String>("내 거래", "내 프로필")
        viewPager?.let {
            TabLayoutMediator(tabLayout, it){ tab, position ->
                tab.text = tabList[position]
            }.attach()
        }

        binding.infoProfileEditBtn.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            val profileSetFragment = ProfileSetFragment()
            profileSetFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, profileSetFragment)
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
