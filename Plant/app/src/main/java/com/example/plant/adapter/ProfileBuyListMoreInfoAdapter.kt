package com.example.plant.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.PlantInfoFragment
import com.example.plant.R
import com.example.plant.model.GetUserBidListResponseItem

class ProfileBuyListMoreInfoAdapter(var mylist: MutableList<GetUserBidListResponseItem>) : RecyclerView.Adapter<ProfileBuyListMoreInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_trade_moreinfo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var stateText = ""
        when (mylist[position].productScoreNo) {
            1 -> stateText = "Very Bad"
            2 -> stateText = "Bad"
            3 -> stateText = "Soso"
            4 -> stateText = "Good"
            5 -> stateText = "Very Good"

        }
        holder.name.text = mylist[position].productName
        holder.state.text = stateText
        holder.price.text = mylist[position].productPrice.toString()


    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.buy_moreinfo_name_tv)
        val state: TextView = itemView.findViewById(R.id.buy_moreinfo_state_tv)
        val price : TextView = itemView.findViewById(R.id.buy_moreinfo_price_tv)
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
