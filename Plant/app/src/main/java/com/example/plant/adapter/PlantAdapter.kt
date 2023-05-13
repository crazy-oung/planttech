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
import com.example.plant.model.Plant

class PlantAdapter(var mylist: MutableList<Plant>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].plantName
        //holder.score.text = mylist[position].plantScore.toString()
        //holder.date.text = mylist[position].startDate.toString()

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putString("name", mylist[position].plantName)
            val cameraFragment = CameraFragment()
            cameraFragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, cameraFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.myPlantName)
        //val score: TextView = itemView.findViewById((R.id.plantScore))
        //val date: TextView = itemView.findViewById(R.id.startDate)


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