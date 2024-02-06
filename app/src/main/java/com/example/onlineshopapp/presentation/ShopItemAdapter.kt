package com.example.onlineshopapp.presentation

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.TableColumnShopItemBinding
import com.example.onlineshopapp.domain.Item

class ShopItemAdapter(
    private val context: Application,
    var listOfShopItems: List<Item>,
    val fragment: CatalogFragment
    ):BaseAdapter() {

    override fun getCount() = listOfShopItems.size

    override fun getItem(p0: Int) = listOfShopItems[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val shopItem = listOfShopItems[p0]
        val bindingItem = TableColumnShopItemBinding.inflate(inflater)

        bindingItem.tvOldPrice.text = "${shopItem.price.price} ${shopItem.price.unit}"
        bindingItem.tvPrice.text = "${shopItem.price.priceWithDiscount} ${shopItem.price.unit}"
        bindingItem.tvSale.text = "-${shopItem.price.discount}%"
        bindingItem.tvTitle.text = shopItem.title
        bindingItem.tvSubtitle.setText(shopItem.subtitle)
        if (shopItem.isFavorite) {
            bindingItem.imageIsNotFavorite.setImageResource(R.drawable.is_favorite)
        }else{
            bindingItem.imageIsNotFavorite.setImageResource(R.drawable.is_not_favorite)
        }
            if (shopItem.feedback != null){
                bindingItem.imageStar.visibility = View.VISIBLE
                bindingItem.tvFeedbackRating.visibility = View.VISIBLE
                bindingItem.tvFeedbackCount.visibility = View.VISIBLE
                bindingItem.tvFeedbackRating.text = "${shopItem.feedback.rating}"
                bindingItem.tvFeedbackCount.text = "(${shopItem.feedback.count})"
            }else{
                bindingItem.imageStar.visibility = View.INVISIBLE
                bindingItem.tvFeedbackRating.visibility = View.INVISIBLE
                bindingItem.tvFeedbackCount.visibility = View.INVISIBLE
            }

            val imagesForShopItem = MainActivity.imagesForShopItems
                .find { images -> images.id == shopItem.id }?.images

            if (imagesForShopItem != null){
                val adapter = ImageShopItemAdapter(imagesForShopItem)
                bindingItem.pagerShopItem.adapter= adapter
                bindingItem.pagerShopItem.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false)
                bindingItem.indicator.attachToRecyclerView(bindingItem.pagerShopItem)

                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(bindingItem.pagerShopItem)
            }
        bindingItem.root.setOnLongClickListener{
            val viewModel = ViewModelProvider(fragment)[CatalogViewModel::class.java]
            viewModel.changeFavoriteShopItem(context,shopItem)
            return@setOnLongClickListener true
        }
        bindingItem.root.setOnClickListener {
            fragment.launchItemFragment(shopItem)
        }
        return bindingItem.root
    }
}