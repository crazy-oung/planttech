package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.Board
import com.example.plant.model.BoardComment
import com.example.plant.model.Plant

class BoardContentAddAdapter(var mylist: MutableList<Board>) : RecyclerView.Adapter<BoardContentAddAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*
        holder.userid.text = mylist[position].userId
        holder.content.text = mylist[position].content
        holder.date.text = mylist[position].date*/

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {/*
        val userid: TextView = itemView.findViewById(R.id.cmt_userid_tv)
        val content: TextView = itemView.findViewById((R.id.cmt_content_tv))
        val date: TextView = itemView.findViewById(R.id.cmt_date_tv)*/
    }

    fun resetRcv(){
        notifyDataSetChanged()
    }
}
