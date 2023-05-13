package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.Plant

class StarAdapter(var mylist: MutableList<Plant>) : RecyclerView.Adapter<StarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_plant, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].plantName
        holder.variety.text = mylist[position].plantVariety
        holder.state.text = mylist[position].plantState
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_name)
        val variety: TextView = itemView.findViewById(R.id.item_variety)
        val state: TextView = itemView.findViewById(R.id.item_state)


    }
}