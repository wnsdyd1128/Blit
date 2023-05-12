package com.example.blit

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.DiffUtil
import com.example.blit.databinding.PurchaseItemListBinding

class PurchaseRecyclerViewAdapter() : androidx.recyclerview.widget.ListAdapter<PurchaseData, PurchaseRecyclerViewAdapter.RecyclerViewHolder>(diffUtil) {
    var dataList = mutableListOf<PurchaseData>()
    companion object {
        var diffUtil = object : DiffUtil.ItemCallback<PurchaseData>(){
            override fun areItemsTheSame(oldItem: PurchaseData, newItem: PurchaseData): Boolean {
                return oldItem.purchase_image_url == newItem.purchase_image_url
            }

            override fun areContentsTheSame(oldItem: PurchaseData, newItem: PurchaseData): Boolean {
                return oldItem == newItem
            }

        }
    }
    inner class RecyclerViewHolder(private val binding: PurchaseItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: PurchaseData) {
            Glide.with(binding.purchaseItemImage.context)
                .load(data.purchase_image_url)
                .centerCrop()
                .placeholder(R.drawable.purchase_image_background)
                .fallback(R.mipmap.coffee)
                .error(R.mipmap.coffee)
                .into(binding.purchaseItemImage)
            binding.firstLineText.text = data.first_line
            binding.secondLineText.text = data.second_line
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = PurchaseItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}

fun RecyclerView?.getCurrentPosition() : Int {
    return (this?.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: 0
}
