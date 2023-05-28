package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.ProfilePlantItemData

class InfoProfileAdapter(var mylist: MutableList<ProfilePlantItemData>) : RecyclerView.Adapter<InfoProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_plant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].plantName
        holder.category.text = mylist[position].plantCategory
        holder.state.text = mylist[position].plantState
        holder.temp.text = mylist[position].temp.toString()
        holder.humi.text = mylist[position].humi.toString()

        /*
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
        }*/
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.profile_item_name_tv)
        val category: TextView = itemView.findViewById((R.id.profile_item_variety_tv))
        val state: TextView = itemView.findViewById(R.id.profile_item_state_tv)
        val temp: TextView = itemView.findViewById(R.id.profile_item_temp_tv)
        val humi: TextView = itemView.findViewById(R.id.profile_item_humi_tv)
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
