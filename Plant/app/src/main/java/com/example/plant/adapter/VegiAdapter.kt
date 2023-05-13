package com.example.plant.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.Plant

class VegiAdapter(var mylist: List<Plant>) : RecyclerView.Adapter<VegiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
        }
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
        val name: TextView = itemView.findViewById(R.id.item_name)
        val variety: TextView = itemView.findViewById(R.id.item_variety)
        val state: TextView = itemView.findViewById(R.id.item_state)
        val star: ImageButton = itemView.findViewById(R.id.star_btn)
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
