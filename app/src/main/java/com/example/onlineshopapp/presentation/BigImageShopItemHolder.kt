package com.example.onlineshopapp.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp.databinding.PageImageBigBinding

class BigImageShopItemHolder (private val pageImageBigBinding: PageImageBigBinding, adapter: BigImageShopItemAdapter)
: RecyclerView.ViewHolder(pageImageBigBinding.root) {

    public fun bind(picture: Int){
        pageImageBigBinding.picture.setImageResource(picture)
    }

}