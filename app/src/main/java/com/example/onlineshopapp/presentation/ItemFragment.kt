package com.example.onlineshopapp.presentation


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.FragmentItemBinding
import com.example.onlineshopapp.databinding.TableRowInfoBinding


class ItemFragment:Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding: FragmentItemBinding
        get() = _binding?: throw RuntimeException("FragmentItemBinding == null")
    private val args by navArgs<ItemFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    @SuppressLint("ResourceType")
    private fun initFragment() {
        val shopItem = args.shopItem
        if (shopItem.isFavorite){
            binding.imageIsNotFavorite.setImageResource(R.drawable.is_favorite)
        }else{
            binding.imageIsNotFavorite.setImageResource(R.drawable.is_not_favorite)
        }
        val imagesForShopItem = MainActivity.imagesForShopItems
            .find { images -> images.id == shopItem.id }?.images

        if (imagesForShopItem != null){
            val adapter = BigImageShopItemAdapter(imagesForShopItem)
            binding.pagerShopItem.adapter= adapter
            binding.pagerShopItem.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false)
            binding.indicator.attachToRecyclerView(binding.pagerShopItem)

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(binding.pagerShopItem)
        }
        binding.tvTitle.text = shopItem.title
        binding.tvSubtitle.text = shopItem.subtitle
        binding.tvAvailable.text = "Доступно для заказа  ${shopItem.available} ${getItemAddition(shopItem.available)}"
        val intRaiting = shopItem.feedback?.rating?.toInt()
        if (intRaiting != null) {

            for (i in 1..intRaiting) {
                launchStar(R.drawable.ic_baseline_star_24)
            }

            if (shopItem.feedback?.rating - intRaiting.toDouble() == 0.0){
                for (i in 1..(5-intRaiting)){
                    launchStar(R.drawable.ic_baseline_star_border_24)
                }
            }else{
                launchStar(R.drawable.ic_baseline_star_half_24)
                for (i in 1..(4-intRaiting)){
                    launchStar(R.drawable.ic_baseline_star_border_24)
                }
            }

        }
        if (shopItem.feedback != null) {
            binding.tvFeedbackRating.text = shopItem.feedback?.rating.toString()
            if (shopItem.feedback?.count != null) {
                binding.tvFeedbackCount.text =
                    "${shopItem.feedback?.count} ${getFeedbsckAddition(shopItem.feedback?.count)}"
            }
        }
        binding.tvPrice.text = shopItem.price.priceWithDiscount
        binding.tvOldPrice.text = shopItem.price.price
        binding.tvSale.text = "- ${shopItem.price.discount}%"
        binding.tvBrand.text = shopItem.title
        binding.description.text = shopItem.description
        binding.tvButtonHide.setOnClickListener{
            binding.description.visibility = ViewGroup.GONE
            binding.clBrand.visibility = ViewGroup.GONE
            binding.tvButtonHide.visibility = ViewGroup.GONE
            binding.tvButtonMore.visibility = ViewGroup.VISIBLE
        }
        binding.tvButtonMore.setOnClickListener{
            binding.description.visibility = ViewGroup.VISIBLE
            binding.clBrand.visibility = ViewGroup.VISIBLE
            binding.tvButtonHide.visibility = ViewGroup.VISIBLE
            binding.tvButtonMore.visibility = ViewGroup.GONE
        }

        for (elInfo in shopItem.info){
            val inflater = LayoutInflater.from(context)
            val bindingItemInfo = TableRowInfoBinding.inflate(inflater)
            bindingItemInfo.edInfo.setText(elInfo.value)
            bindingItemInfo.edInfoName.setText(elInfo.title)
            binding.tlInfo.addView(bindingItemInfo.root)
        }
        binding.etIngredients.setText(shopItem.ingredients)
        binding.tvButtonHideIngredients.setOnClickListener{
            binding.etIngredients.visibility = ViewGroup.GONE
            binding.tvButtonHideIngredients.visibility = ViewGroup.GONE
            binding.tvButtonMoreIngredients.visibility = ViewGroup.VISIBLE
        }
        binding.tvButtonMoreIngredients.setOnClickListener{
            binding.etIngredients.visibility = ViewGroup.VISIBLE
            binding.tvButtonHideIngredients.visibility = ViewGroup.VISIBLE
            binding.tvButtonMoreIngredients.visibility = ViewGroup.GONE
        }

        binding.button.text = "Добавить корзину"

    }


    private fun launchStar(id:Int) {
        val imageView = ImageView(requireContext())
        imageView.setImageResource(id)
        val imageViewLayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        imageView.layoutParams = imageViewLayoutParams
        binding.llStars.addView(imageView)
    }

    fun getItemAddition(num: Int): String? {
        val preLastDigit = num % 100 / 10
        return if (preLastDigit == 1) {
            "штук"
        } else when (num % 10) {
            1 -> "штука"
            2, 3, 4 -> "штуки"
            else -> "штук"
        }
    }

    fun getFeedbsckAddition(num: Int): String? {
        val preLastDigit = num % 100 / 10
        return if (preLastDigit == 1) {
            "отзывов"
        } else when (num % 10) {
            1 -> "отзыв"
            2, 3, 4 -> "отзыва"
            else -> "отзывов"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}