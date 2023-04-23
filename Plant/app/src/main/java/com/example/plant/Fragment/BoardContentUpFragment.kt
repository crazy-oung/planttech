package com.example.plant.Fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.R
import com.example.plant.databinding.FragmentBoardContentUpBinding
import com.example.plant.model.Board
import java.util.*

class BoardContentUpFragment : Fragment() {

    lateinit var data : MutableList<Board>
    var mFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBoardContentUpBinding.inflate(inflater, container, false)

        binding.regButton.setOnClickListener {
            var mNow = System.currentTimeMillis()
            var mDate = Date(mNow)
            data = mutableListOf(
                Board(
                    binding.titleEt.toString(),
                    "유저 아이디 추가",
                    mFormat.format(mDate),
                    binding.contentEt.toString())
            )
            // userId는 로그인 이후 유저의 id를 받아서 넣음.
            // data를 API를 통해 전달
            MainActivity().supportFragmentManager.beginTransaction().replace(com.google.android.material.R.id.container, BoardFragment()).commit()
            // 이후 메인엑티비티에서 요청 후 게시글로 다시 복귀
        }

        return binding.root
    }

}