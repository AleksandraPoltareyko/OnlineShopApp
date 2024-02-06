package com.example.onlineshopapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


import com.example.onlineshopapp.R
import com.example.onlineshopapp.data.ImagesForShopItems
import com.example.onlineshopapp.databinding.ActivityMainBinding
import com.example.onlineshopapp.domain.Item
import com.example.onlineshopapp.domain.User
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),Postman {

    lateinit var binding: ActivityMainBinding

    lateinit var _bottomNavigationview: BottomNavigationView
    val bottomNavigationView:BottomNavigationView
    get() = _bottomNavigationview

    init {
        initImagesForShopItems()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigation: BottomNavigationView = binding.bottom

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.mainFragment,
            R.id.catalogFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)
    }

    private fun initImagesForShopItems() {
        imagesForShopItems.add(ImagesForShopItems(
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
            listOf(R.drawable.shop_item_image6, R.drawable.shop_item_image5)))
        imagesForShopItems.add(ImagesForShopItems(
            "54a876a5-2205-48ba-9498-cfecff4baa6e",
            listOf(R.drawable.shop_item_image1, R.drawable.shop_item_image2)))
        imagesForShopItems.add(ImagesForShopItems(
            "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
            listOf(R.drawable.shop_item_image5, R.drawable.shop_item_image6)))
        imagesForShopItems.add(ImagesForShopItems(
            "16f88865-ae74-4b7c-9d85-b68334bb97db",
            listOf(R.drawable.shop_item_image3, R.drawable.shop_item_image4)))
        imagesForShopItems.add(ImagesForShopItems(
            "26f88856-ae74-4b7c-9d85-b68334bb97db",
            listOf(R.drawable.shop_item_image2, R.drawable.shop_item_image3)))
        imagesForShopItems.add(ImagesForShopItems(
            "15f88865-ae74-4b7c-9d81-b78334bb97db",
            listOf(R.drawable.shop_item_image6, R.drawable.shop_item_image1)))
        imagesForShopItems.add(ImagesForShopItems(
            "88f88865-ae74-4b7c-9d81-b78334bb97db",
            listOf(R.drawable.shop_item_image4, R.drawable.shop_item_image3)))
        imagesForShopItems.add(ImagesForShopItems(
            "55f58865-ae74-4b7c-9d81-b78334bb97db",
            listOf(R.drawable.shop_item_image1, R.drawable.shop_item_image5)))
    }


    companion object{
        private var _user: User? = null
        var user : User? = null
            get() = _user

        private var _shopList: List<Item>? = null
        var shopList : List<Item>? = null
            get() = _shopList
        val imagesForShopItems = mutableListOf<ImagesForShopItems>()
    }

    override fun fragmentMail(user: User?, shopList: List<Item>?) {
       if (user != null) {
           _user = user
       }
        if (shopList != null){
            _shopList = shopList
        }
    }
}