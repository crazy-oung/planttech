package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class GraphAdapter(private val data: List<List<Float>>) : RecyclerView.Adapter<GraphAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chart: BarChart = itemView.findViewById(R.id.barChart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_graph, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entries = mutableListOf<BarEntry>()
        for (i in data[position].indices) {
            entries.add(BarEntry(i.toFloat(), data[position][i]))
        }

        val dataSet = BarDataSet(entries, "Label")
        val barData = BarData(dataSet)

        holder.chart.data = barData
        holder.chart.description.isEnabled = false
        holder.chart.axisLeft.isEnabled = false
        holder.chart.axisRight.isEnabled = false
        holder.chart.legend.isEnabled = false
        holder.chart.setDrawValueAboveBar(false)
        holder.chart.setFitBars(true)
        holder.chart.invalidate()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}