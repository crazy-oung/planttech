package com.example.plant.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.adapter.BoardContentAdapter
import com.example.plant.databinding.FragmentBoardContentBinding
import com.example.plant.model.Board
import com.example.plant.model.BoardComment
import java.util.*

class BoardContentFragment : Fragment(R.layout.fragment_board_content) {
    lateinit var recyclerView : RecyclerView
    lateinit var boardContentAdapter: BoardContentAdapter
    lateinit var boardData : MutableList<Board>
    lateinit var data : MutableList<BoardComment>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentBoardContentBinding.inflate(inflater, container, false)

        boardData = mutableListOf(
            Board(0,0,0,
            "방울토마토 5kg 거래합니다!","2","2","50,000원","13:12","1",),
            Board(1,0,0,
                "관상용 부레옥잠 입니다","2","2","30,000원","2023-05-10","1",)
        )

        data = mutableListOf(
            BoardComment("홍길동", "15:12", "구매 의향 있습니다 연락주세요!")
        )
        // data = api 정보 추가
        boardContentAdapter = context?.let { BoardContentAdapter(data) }!!
        binding.commentRcv.adapter = boardContentAdapter


        recyclerView = binding.commentRcv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BoardContentAdapter(data) // data는 댓글 정보 불러와서 넣기

        binding.regButton.setOnClickListener {
            // 댓글 내용 추가
            boardContentAdapter.resetRcv() // 추가 이후 갱신
        }


        return binding.root
    }

}