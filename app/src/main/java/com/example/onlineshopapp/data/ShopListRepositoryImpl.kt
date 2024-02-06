package com.example.onlineshopapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlineshopapp.data.api.ApiFactory
import com.example.onlineshopapp.domain.Item
import com.example.onlineshopapp.domain.ListOfShopItems
import com.example.onlineshopapp.domain.ShopListRepository
import com.example.onlineshopapp.presentation.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ShopListRepositoryImpl: ShopListRepository {

    private lateinit var shopList : ListOfShopItems
    private val _shopListLD = MutableLiveData<ListOfShopItems>()
    val shopListLD: LiveData<ListOfShopItems>
        get() = _shopListLD

    private fun loadData() {
        ApiFactory.apiSrevice.getShopList().enqueue(object : Callback<ListOfShopItems> {
            override fun onResponse(call: Call<ListOfShopItems>, response: Response<ListOfShopItems>) {
                if(response.code() < 400) {
                    val body = response.body()
                    if (body != null){
                        if (MainActivity.user != null){
                            val listOfFavoriteId = parseFavorites(MainActivity.user!!.favorites)
                            for (favoriteId in listOfFavoriteId){
                                val findShopItem = body.items.find { it.id ==  favoriteId}
                                if (findShopItem != null){
                                    findShopItem.isFavorite = true
                                }
                            }
                        }
                        shopList = body
                        updateList()
                    }else{
                        throw RuntimeException("Api return null")
                    }
                }
            }

            override fun onFailure(call: Call<ListOfShopItems>, t: Throwable) {
                throw RuntimeException("onFailure: ${t.message}")
            }
        })
    }

    fun parseFavorites(favorites:String):List<String>{
        if (favorites.isEmpty()){
            return listOf()
        }else{
            return favorites.split(";")
        }

    }

    override fun getItem(id: String): Item {
        return shopList.items.find { it.id == id } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getShopList(): LiveData<ListOfShopItems> {
        loadData()
        return shopListLD
    }

    private fun updateList(){
        _shopListLD.value = shopList
    }

}