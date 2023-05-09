package com.example.blit

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blit.databinding.ItemListBinding

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){
    var dataList = mutableListOf<ProfileData>()
    var flag : Boolean = true
    inner class RecyclerViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ProfileData) {
            when (flag) {
                true -> Glide.with(binding.userProfileImageEllipse.context)
                        .load(data.profile_image_url)
                        .fallback(R.mipmap.flower_foreground)
                        .error(R.mipmap.flower_foreground)
                        .into(binding.userProfileImageEllipse)
                false -> Glide.with(binding.userProfileImageEllipse.context)
                        .load(data.profile_image_url)
                        .fallback(R.mipmap.flower_foreground_2)
                        .error(R.mipmap.flower_foreground_2)
                        .into(binding.userProfileImageEllipse)
            }
            flag = !flag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}

class ItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right += offset
    }
}