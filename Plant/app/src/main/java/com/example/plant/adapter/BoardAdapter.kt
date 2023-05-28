package com.example.plant.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.BoardContentFragment
import com.example.plant.Fragment.CameraFragment
import com.example.plant.R
import com.example.plant.model.Board
import com.example.plant.model.Plant

class BoardAdapter(var mylist: List<Board>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].articleTitle
        holder.price.text = mylist[position].articleProductPrice
        holder.date.text = mylist[position].articleCreatetime
        holder.explain.text = mylist[position].articleContent
        holder.state.text = mylist[position].articleSubject

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putString("title", mylist[position].articleTitle)
            bundle.putString("createTime", mylist[position].articleCreatetime)
            bundle.putString("content", mylist[position].articleContent)
            bundle.putString("price", mylist[position].articleProductPrice)
            val boardContentFragment = BoardContentFragment()
            boardContentFragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, boardContentFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById((R.id.item_price))
        val date: TextView = itemView.findViewById(R.id.item_date)
        val explain: TextView = itemView.findViewById(R.id.item_exp)
        val state: TextView = itemView.findViewById(R.id.item_state)
    }

    interface ItemClickListener{
        fun onClick(view: View,position: Int)
    }
    //를릭 리스너
    private lateinit var itemClickListner: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

}