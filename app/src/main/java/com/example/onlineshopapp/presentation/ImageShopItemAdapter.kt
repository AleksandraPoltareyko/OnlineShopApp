package com.example.onlineshopapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.PageImageBinding

class ImageShopItemAdapter(private val pictures: List<Int>):RecyclerView.Adapter<ImageShopItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageShopItemHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val pageImageBinding = PageImageBinding.inflate(inflater, parent, false)
        return ImageShopItemHolder(pageImageBinding, this)
    }

    override fun getItemViewType(position: Int)=  R.layout.page_image

    override fun onBindViewHolder(holder: ImageShopItemHolder, position: Int) = holder.bind(pictures[position])

    override fun getItemCount() = pictures.size
}