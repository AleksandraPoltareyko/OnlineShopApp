package com.example.onlineshopapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.PageImageBigBinding

class BigImageShopItemAdapter (private val pictures: List<Int>): RecyclerView.Adapter<BigImageShopItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigImageShopItemHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val pageBigImageBinding = PageImageBigBinding.inflate(inflater, parent, false)
        return BigImageShopItemHolder(pageBigImageBinding, this)
    }

    override fun getItemViewType(position: Int)=  R.layout.page_image

    override fun getItemCount() = pictures.size

    override fun onBindViewHolder(holder: BigImageShopItemHolder, position: Int) = holder.bind(pictures[position])
}