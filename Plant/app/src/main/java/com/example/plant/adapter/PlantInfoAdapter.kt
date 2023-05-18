package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.PlantMoreInfo

class PlantInfoAdapter(var mylist: List<PlantMoreInfo>) : RecyclerView.Adapter<PlantInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant_moreinfo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mylist[position].title
        holder.content.text = mylist[position].content
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.plant_info_moreInfo_title_tv)
        val content: TextView = itemView.findViewById(R.id.plant_info_moreInfo_content_tv)
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
