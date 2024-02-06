package com.example.onlineshopapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onlineshopapp.data.ShopListRepositoryImpl
import com.example.onlineshopapp.databinding.FragmentUserBinding

private var _binding: FragmentUserBinding? = null
private val binding: FragmentUserBinding
    get() = _binding?: throw RuntimeException("FragmentUserBinding == null")

class UserFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (MainActivity.user != null) {
            binding.tvUserName.text = "${MainActivity.user!!.name} ${MainActivity.user!!.surname}"
            binding.tvPhone.text = "${MainActivity.user!!.phone}"
            val countOfFavorites = ShopListRepositoryImpl.parseFavorites(MainActivity.user!!.favorites)
            binding.tvFavoritesInfo.text = "${countOfFavorites.size-1} ${getItemAddition(countOfFavorites.size)}"
            binding.clFavorite.setOnClickListener {
                findNavController().navigate(UserFragmentDirections.actionUserFragmentToFavoritesFragment2())
            }
        }
    }

    private fun getItemAddition(num: Int): String? {
        val preLastDigit = num % 100 / 10
        return if (preLastDigit == 1) {
            "товаров"
        } else when (num % 10) {
            1 -> "товар"
            2, 3, 4 -> "товара"
            else -> "товаров"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}