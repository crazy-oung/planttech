package com.example.plant.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.PlantInfoFragment
import com.example.plant.R
import com.example.plant.model.HomePlantItemData

class FruitAdapter(var mylist: MutableList<HomePlantItemData>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_plant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickName.text = mylist[position].plantNickname
        holder.name.text = mylist[position].plantName
        holder.variety.text = mylist[position].plantCategory
        holder.state.text = mylist[position].plantState
        holder.temp.text = mylist[position].plantTemp.toString() + " ℃"
        holder.humi.text = mylist[position].plantHumi.toString() + " %"

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putInt("plantNo", mylist[position].plantNo)
            bundle.putInt("plantWHNo", mylist[position].plantWarehouseNo)
            bundle.putString("plantNickname", mylist[position].plantNickname)
            bundle.putString("plantState", mylist[position].plantState)
            val plantInfoFragment = PlantInfoFragment()
            plantInfoFragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, plantInfoFragment)
                .addToBackStack(null)
                .commit()
        }

        /*
        북마크 기능
        if(mylist[position].plantStar)
            holder.star.setImageResource(android.R.drawable.btn_star_big_on)
        else
            holder.star.setImageResource(android.R.drawable.btn_star_big_off)

        holder.star.setOnClickListener {
            if(mylist[position].plantStar){
                holder.star.setImageResource(android.R.drawable.btn_star_big_off)
                mylist[position].plantStar = false
            }
            else{
                holder.star.setImageResource(android.R.drawable.btn_star_big_on)
                mylist[position].plantStar = true
            }
        }*/

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nickName: TextView = itemView.findViewById(R.id.item_plant_nickname)
        val name: TextView = itemView.findViewById(R.id.item_plant_name)
        val variety: TextView = itemView.findViewById(R.id.item_variety)
        val state: TextView = itemView.findViewById(R.id.item_state)
        val temp: TextView= itemView.findViewById(R.id.item_temp)
        val humi: TextView= itemView.findViewById(R.id.item_humi)
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
