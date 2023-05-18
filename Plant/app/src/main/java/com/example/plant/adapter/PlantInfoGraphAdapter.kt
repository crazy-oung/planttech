package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class PlantInfoGraphAdapter(private val data: List<List<Float>>) : RecyclerView.Adapter<PlantInfoGraphAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chart: LineChart = itemView.findViewById(R.id.info_barChart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant_graph, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entries = mutableListOf<BarEntry>()
        for (i in data[position].indices) {
            entries.add(BarEntry(i.toFloat(), data[position][i]))
        }
/*
        val labels = arrayListOf<String>()
        labels.add("2023-05-10")
        labels.add("2023-05-11")
        labels.add("2023-05-12")
        labels.add("2023-05-13")
        labels.add("2023-05-14")
        labels.add("2023-05-15")
        labels.add("2023-05-16")*/
        val dataSet = LineDataSet(entries as List<Entry>?, "Label")
        val lineData = LineData(dataSet)

        lineData.setValueTextSize(12f)
        holder.chart.data = lineData
        holder.chart.description.isEnabled = false
        holder.chart.axisLeft.isEnabled = false
        holder.chart.axisRight.isEnabled = false
        holder.chart.legend.isEnabled = false
        holder.chart.invalidate()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}