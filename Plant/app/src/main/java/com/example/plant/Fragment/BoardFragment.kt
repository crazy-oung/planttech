package com.example.plant.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.adapter.BoardAdapter
import com.example.plant.adapter.BoardPagerFragmentStateAdapter
import com.example.plant.adapter.HomePagerFragmentStateAdapter
import com.example.plant.api.RetrofitService
import com.example.plant.databinding.FragmentBoardBinding
import com.example.plant.model.Board
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class BoardFragment : Fragment(R.layout.fragment_board) {
    lateinit var mainActivity: MainActivity
    private var viewPager: ViewPager2? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var data : List<Board>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentBoardBinding.inflate(inflater, container, false)

        val retrofit = Retrofit.Builder().baseUrl("http://dayounghan.com/") //http://192.168.0.21:8080/ //http://dayounghan.com/
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)

        data = mutableListOf(Board(
            0, 0, 0,
            "토마토", "Good", "5Kg, 당도 높음", "50,000원", "13:12", "1",
        ),
            Board(
                1, 0, 0,
                "부레옥잠", "Soso", "수상생물", "30,000원", "2023-05-10", "1",
            ),
            Board(
                2, 0, 0,
                "난", "Good", "1M, 사진 참고", "100,000원", "2023-05-08", "1",
            ))

        viewPager = binding.boardPager
        tabLayout = binding.boardTabLayout

        val pagerAdapter = BoardPagerFragmentStateAdapter(requireActivity())


        pagerAdapter.addFragment(BoardAllListFragment())
        pagerAdapter.addFragment(BoardRecommendListFragment())
        pagerAdapter.addFragment(BoardRankListFragment())
        pagerAdapter.addFragment(BoardFruitListFragment())
        pagerAdapter.addFragment(BoardVegetableListFragment())
        pagerAdapter.addFragment(BoardOrnamentalListFragment())
        // adapter 연결
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabList = listOf<String>("전체", "추천", "랭킹", "과일", "채소", "관상식물")
        viewPager?.let {
            TabLayoutMediator(tabLayout, it){ tab, position ->
                tab.text = tabList[position]
            }.attach()
        }

/*
        service.boardGet()?.enqueue(object : Callback<List<Board>> {
            override fun onResponse(call: Call<List<Board>>, response: Response<List<Board>>) {
                if(response.isSuccessful){
                    // 정상적으로 통신이 성공된 경우
                    data = response.body()!!
                    Log.d("Gooood", data.get(0).articleTitle)
                    boardAdapter = context?.let { BoardAdapter(data) }!!
                    binding.boardRcv.adapter = boardAdapter

                    // val intent = Intent(this.context, CameraFragment::class.java)
                    recyclerView = binding.boardRcv
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = BoardAdapter(data)
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    data = mutableListOf(Board(
                        0, 0, 0,
                        "방울토마토 5kg 거래합니다!", "2", "2", "50,000원", "13:12", "1",
                    ),
                        Board(
                            1, 0, 0,
                            "관상용 부레옥잠 입니다", "2", "2", "30,000원", "2023-05-10", "1",
                        ))
                    Log.d("Baaaad", "onResponse 실패")
                    boardAdapter = context?.let { BoardAdapter(data) }!!
                    binding.boardRcv.adapter = boardAdapter

                    // val intent = Intent(this.context, CameraFragment::class.java)
                    recyclerView = binding.boardRcv
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = BoardAdapter(data)
                }
            }
            override fun onFailure(call: Call<List<Board>>, t: Throwable) {
                // 통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                data = mutableListOf(Board(
                    0, 0, 0,
                    "예외발생", "1", "1", "1", "1", "1",
                ))
                Log.d("Very Baaaad", "onFailure 에러: " + t.message.toString())
            }
        })

        Log.d("Gooood2", data.get(0).articleTitle)
*/


            /* 가습기 작동 테스트 코드
                service.humiRequest(0, 1)?.enqueue(object : Callback<LedResponse> {
                    override fun onResponse(
                        call: Call<LedResponse>,
                        response: Response<LedResponse>
                    ) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성공된 경우
                            Log.d("Gooood", "성공")
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("Baaaad", "가습기 onResponse 실패")
                        }
                    }
                    override fun onFailure(call: Call<LedResponse>, t: Throwable) {
                        // 통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                       Log.d("Very Baaaad", "onFailure 에러: ")
                    }
                })*/


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

}