package com.example.plant.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.CameraFragment
import com.example.plant.R
import com.example.plant.model.BoardTradeList
import com.example.plant.model.Plant

class BoardSellListPlantAdapter(var mylist: MutableList<BoardTradeList>) : RecyclerView.Adapter<BoardSellListPlantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board_trade, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.state.text = mylist[position].state
        holder.price.text = mylist[position].price.toString()
        holder.date.text = mylist[position].date




    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val state: TextView = itemView.findViewById(R.id.board_info_item_trade_state_tv)
        val price: TextView = itemView.findViewById(R.id.board_info_item_trade_price_tv)
        val date: TextView = itemView.findViewById(R.id.board_info_item_trade_date_tv)




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