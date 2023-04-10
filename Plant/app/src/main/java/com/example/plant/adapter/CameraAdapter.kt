package com.example.plant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R
import com.example.plant.model.CameraRecyclerInViewModel
import com.example.plant.model.RecyclerInViewModel
import com.example.plant.model.multi_type1

class CameraAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas = mutableListOf<CameraRecyclerInViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View
        return when(viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_camera_display,
                    parent,
                    false
                )
                MultiViewHolder1(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_camera_info,
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

        private var video : ImageView = view.findViewById(R.id.CamaerView)
        //private val imgProfile: ImageView = view.findViewById(R.id.exampleView)

        fun bind(item: CameraRecyclerInViewModel) {
            video.setImageBitmap(item.cameraView)
        }
    }
    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {

        private val plantName: TextView = view.findViewById(R.id.camera_Info_PlantName)
        private val plantState: TextView = view.findViewById(R.id.camera_Info_PlantState)
        private val temp: TextView = view.findViewById(R.id.camera_Info_temp)
        private val humidity: TextView = view.findViewById(R.id.camera_Info_humi)

        fun bind(item: CameraRecyclerInViewModel) {
            plantName.text = item.plantName
            plantState.text = item.plantState
            temp.text = item.temp.toString()
            humidity.text = item.humidity.toString()
        }
    }
}