package com.example.plant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.RecyclerInViewModel
import com.example.plant.model.multi_type1
import com.example.plant.model.multi_type2

class InfoAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas = mutableListOf<RecyclerInViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View
        return when(viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_info_mileage,
                    parent,
                    false
                )
                MultiViewHolder1(view)
            }
            multi_type2 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_info_purchase,
                    parent,
                    false
                )
                MultiViewHolder2(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_info_like,
                    parent,
                    false
                )
                MultiViewHolder3(view)
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
                (holder as MultiViewHolder1)
                holder.setIsRecyclable(false)
            }
            multi_type2 -> {
                (holder as MultiViewHolder2)
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as MultiViewHolder3)
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
/*
        private val palntName: TextView = view.findViewById(R.id.plantName)
        private val temp: TextView = view.findViewById(R.id.temp)
        private val humidity: TextView = view.findViewById(R.id.humidity)
        //private val imgProfile: ImageView = view.findViewById(R.id.exampleView)

        fun bind(item: RecyclerInViewModel) {
            palntName.text = item.plantName
            temp.text = item.temp.toString()
            humidity.text = item.humidity.toString()

        }*/
    }
    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
/*
        val chart: BarChart = itemView.findViewById(R.id.homeBarChart)

        fun bind(item: RecyclerInViewModel) {
            val entries = mutableListOf<BarEntry>()
            for (i in data.indices) {
                entries.add(BarEntry(i.toFloat(), data[i]))
            }

            val dataSet = BarDataSet(entries, "Label")
            val barData = BarData(dataSet)

            chart.data = barData
            chart.description.isEnabled = false
            chart.axisLeft.isEnabled = false
            chart.axisRight.isEnabled = false
            chart.legend.isEnabled = false
            chart.setDrawValueAboveBar(false)
            chart.setFitBars(true)
            chart.invalidate()
        }*/
    }
    inner class MultiViewHolder3(view : View) : RecyclerView.ViewHolder(view) {
/*
        private val palntName: TextView = view.findViewById(R.id.plantName)
        private val temp: TextView = view.findViewById(R.id.temp)
        private val humidity: TextView = view.findViewById(R.id.humidity)
        //private val imgProfile: ImageView = view.findViewById(R.id.exampleView)

        fun bind(item: RecyclerInViewModel) {
            palntName.text = item.plantName
            temp.text = item.temp.toString()
            humidity.text = item.humidity.toString()

        }*/
    }
}