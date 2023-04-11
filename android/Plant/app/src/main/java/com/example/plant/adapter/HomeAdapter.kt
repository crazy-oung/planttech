package com.example.plant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.RecyclerInViewModel
import com.example.plant.model.multi_type1

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas = mutableListOf<RecyclerInViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View
        return when(viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_main_plant,
                    parent,
                    false
                )
                MultiViewHolder1(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_main_graph,
                    parent,
                    false
                )
                MultiViewHolder2(view)
            }
        }
        //val binding = FragmentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun getItemViewType(position: Int): Int {
        return datas[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(datas[position].type) {
            multi_type1 -> {
                (holder as MultiViewHolder1).bind(datas[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as MultiViewHolder2).bind(datas[position])
                holder.setIsRecyclable(false)
            }
        }
    }
/*
    inner class ViewHolder(var binding: FragmentHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerInViewModel) {
            binding.homeRcv
        }
    }
*/
    inner class MultiViewHolder1(view : View) : RecyclerView.ViewHolder(view) {

        private val palntName: TextView = view.findViewById(R.id.plantName)
        private val temp: TextView = view.findViewById(R.id.temp)
        private val humidity: TextView = view.findViewById(R.id.humidity)
        //private val imgProfile: ImageView = view.findViewById(R.id.exampleView)

        fun bind(item: RecyclerInViewModel) {
            palntName.text = item.plantName
            temp.text = item.temp.toString()
            humidity.text = item.humidity.toString()

        }
    }
    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: RecyclerInViewModel) {

        }
    }
}