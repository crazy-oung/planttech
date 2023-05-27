package com.example.plant.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.Fragment.BoardContentFragment
import com.example.plant.Fragment.BoardInfoFragment
import com.example.plant.Fragment.CameraFragment
import com.example.plant.Fragment.PlantInfoFragment
import com.example.plant.R
import com.example.plant.model.Board
import com.example.plant.model.Plant
import java.text.DecimalFormat

class BoardAllListAdapter(var mylist: List<Board>) : RecyclerView.Adapter<BoardAllListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_plant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mylist[position].articleTitle
        holder.price.text = mylist[position].articleProductPrice
        holder.variety.text = mylist[position].articleContent
        holder.state.text = mylist[position].articleSubject

        val decimal = DecimalFormat("#,###")
        holder.amount.text = decimal.format(mylist[position].plantAmount) + " 개"


        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putInt("plantNumber", mylist[position].plantNo!!)
            bundle.putString("plantCategory", mylist[position].plantCategory)
            val boardInfoFragment = BoardInfoFragment()
            boardInfoFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, boardInfoFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.shop_item_name)
        val price: TextView = itemView.findViewById((R.id.shop_item_price))
        val variety: TextView = itemView.findViewById(R.id.shop_item_variety)
        val state: TextView = itemView.findViewById(R.id.shop_item_state)
        val amount : TextView = itemView.findViewById(R.id.shop_item_amount)
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
