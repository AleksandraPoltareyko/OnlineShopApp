package com.example.onlineshopapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlineshopapp.R
import com.example.onlineshopapp.databinding.FragmentFavoritesBinding
import com.example.onlineshopapp.domain.Item

private var _binding: FragmentFavoritesBinding? = null
private val binding: FragmentFavoritesBinding
    get() = _binding?: throw RuntimeException("FragmentFavoritesBinding == null")

private lateinit var adapter : ShopItemAdapterForFavorite

class FavoritesFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container,false)

        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listOfFavorites = MainActivity.shopList?.filter { it.isFavorite }
        if (listOfFavorites != null) {
            launchfavorites(listOfFavorites)
        }
        binding.tvBrand.setOnClickListener{
            binding.gridFavoriteList.visibility = ViewGroup.GONE
            binding.tvBrand.setTextColor(R.color.dark_grey)
            binding.tvShopItems.setTextColor(R.color.grey)
        }
        binding.tvShopItems.setOnClickListener{
            binding.gridFavoriteList.visibility = ViewGroup.VISIBLE
            binding.tvBrand.setTextColor(R.color.grey)
            binding.tvShopItems.setTextColor(R.color.dark_grey)
        }

    }

    private fun launchfavorites(listOfFavorites: List<Item>) {
        adapter = ShopItemAdapterForFavorite(requireActivity().application,listOfFavorites, this)
        binding.gridFavoriteList.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}