package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.AlamContent
import com.example.plant.model.AlamSettingContent

class AlamTradeSettingAdapter(var mylist: List<AlamSettingContent>) : RecyclerView.Adapter<AlamTradeSettingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alam_setting, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mylist[position].alamTitle
        holder.content.text = mylist[position].alamContent
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
        val title: TextView = itemView.findViewById(R.id.alam_setting_title)
        val content: TextView = itemView.findViewById(R.id.alam_setting_content)
        // 이미지도 포함???
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
