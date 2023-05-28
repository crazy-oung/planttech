package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class PlantInfoGraphAdapter(private val data: List<List<Float>>) : RecyclerView.Adapter<PlantInfoGraphAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chart: LineChart = itemView.findViewById(R.id.info_barChart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant_info_graph, parent, false)
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
        holder.chart.xAxis.isEnabled = false

        holder.chart.legend.orientation = Legend.LegendOrientation.VERTICAL
        holder.chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        holder.chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        holder.chart.legend.textSize = 15F
        holder.chart.legend.form = Legend.LegendForm.LINE

        holder.chart.legend.isEnabled = false

        holder.chart.description.isEnabled = true

        holder.chart.setTouchEnabled(true)

        lineData.setValueTextSize(15f)
        lineData.setDrawValues(false)
        holder.chart.data = lineData
        holder.chart.axisRight.setDrawGridLines(false)
        holder.chart.description.isEnabled = false
        holder.chart.axisLeft.isEnabled = false
        holder.chart.axisRight.isEnabled = true
        holder.chart.legend.isEnabled = false
        holder.chart.invalidate()
    }

    override fun getItemCount(): Int {
        if(data.size > 6){
            return 5;
        }
        else
        {
            return data.size;
        }
    }
}