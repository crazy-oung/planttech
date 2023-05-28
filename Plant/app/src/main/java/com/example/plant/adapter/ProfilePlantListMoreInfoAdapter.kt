package com.example.plant.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.BoardContentFragment
import com.example.plant.Fragment.GetUserPlantResponseItem
import com.example.plant.Fragment.PlantInfoFragment
import com.example.plant.R

class ProfilePlantListMoreInfoAdapter(var mylist: MutableList<GetUserPlantResponseItem>) : RecyclerView.Adapter<ProfilePlantListMoreInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_plant_moreinfo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].userPlantName
        holder.category.text = mylist[position].plantKoreanName
        holder.state.text = "Very Good"

        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putString("plantNickname", mylist[position].userPlantName)
            bundle.putInt("plantNo", mylist[position].plantNo!!)
            bundle.putString("state", "Very Good")
            val plantInfoFragment = PlantInfoFragment()
            plantInfoFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, plantInfoFragment)
                .addToBackStack(null)
                .commit()
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.plant_moreinfo_name_tv)
        val category: TextView = itemView.findViewById((R.id.plant_moreinfo_category_tv))
        val state: TextView = itemView.findViewById(R.id.plant_moreinfo_state_tv)
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
