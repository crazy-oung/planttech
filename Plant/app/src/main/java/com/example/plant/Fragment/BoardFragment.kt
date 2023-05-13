package com.example.plant.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardAdapter
import com.example.plant.api.RetrofitService
import com.example.plant.databinding.FragmentBoardBinding
import com.example.plant.model.Board
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class BoardFragment : Fragment(R.layout.fragment_board) {
    lateinit var recyclerView : RecyclerView
    lateinit var boardAdapter: BoardAdapter
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
        boardAdapter = context?.let { BoardAdapter(data) }!!
        binding.boardRcv.adapter = boardAdapter

        // val intent = Intent(this.context, CameraFragment::class.java)
        recyclerView = binding.boardRcv
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = BoardAdapter(data)
        binding.boardRcv.setHasFixedSize(true)


        binding.floatingActionButton.setOnClickListener{
            val bundle = Bundle()
            val boardContentAddFragment = BoardContentAddFragment()
            boardContentAddFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.fragmentContainer, boardContentAddFragment)
                ?.addToBackStack(null)
                ?.commit()
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
            }


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_board, menu)
        super.onCreateOptionsMenu(menu!!, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                true
            }
            R.id.delete -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}