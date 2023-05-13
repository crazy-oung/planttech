package com.example.plant.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.CameraFragment
import com.example.plant.R
import com.example.plant.model.Plant
import com.example.plant.model.RecyclerInViewModel
import com.example.plant.model.multi_type1
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class HomeAdapter(var mylist: MutableList<Plant>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_plant, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].plantName
        holder.state.text = mylist[position].plantState
        //holder.date.text = mylist[position].startDate.toString()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_tv)
        val state: TextView = itemView.findViewById((R.id.plant_state_tv))
        //val date: TextView = itemView.findViewById(R.id.startDate)


    }
}