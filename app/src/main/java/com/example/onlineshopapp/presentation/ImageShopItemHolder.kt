package com.example.onlineshopapp.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp.databinding.PageImageBinding

class ImageShopItemHolder(private val pageImageBinding: PageImageBinding, adapter: ImageShopItemAdapter)
    :RecyclerView.ViewHolder(pageImageBinding.root) {

    public fun bind(picture: Int){
        pageImageBinding.picture.setImageResource(picture)
    }

}