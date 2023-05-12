package com.example.blit

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blit.databinding.StageDataItemListBinding

class StageRecyclerViewAdapter() : RecyclerView.Adapter<StageRecyclerViewAdapter.RecyclerViewHolder>(){
    var dataList = mutableListOf<StageData>()
    var flag : Boolean = true
    inner class RecyclerViewHolder(private val binding: StageDataItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: StageData) {
            binding.stageTextView.text = data.stage
            binding.placeTextView.text = data.place
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = StageDataItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}
