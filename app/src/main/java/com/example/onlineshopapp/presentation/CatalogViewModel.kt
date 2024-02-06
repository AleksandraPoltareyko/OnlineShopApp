package com.example.onlineshopapp.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.data.ShopListRepositoryImpl
import com.example.onlineshopapp.data.UserRepositoryImpl
import com.example.onlineshopapp.domain.*
import kotlinx.coroutines.launch

class CatalogViewModel:ViewModel() {
    private val shopListRepository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepository)

    private lateinit var _shopList: LiveData<ListOfShopItems>
    val shopList: LiveData<ListOfShopItems>
        get() = _shopList

    private var _needUpdateShopList = MutableLiveData<Unit>()
    val needUpdateShopList: LiveData<Unit>
        get() = _needUpdateShopList

    fun getShopList(){
        _shopList = getShopListUseCase.getShopList()
        _needUpdateShopList.value = Unit

    }

    fun sortShopList(typeOfSort: Int){

        when(typeOfSort) {
            0 -> {
                val temp_sl = _shopList.value?.items?.sortedByDescending { it.feedback?.rating }
                _shopList.value?.items= temp_sl as MutableList<Item>
            }
            1 -> {
                val temp_sl = _shopList.value?.items?.sortedByDescending { it.price.priceWithDiscount.toInt() }
                _shopList.value?.items= temp_sl as MutableList<Item>
            }
            2 -> {
                val temp_sl = _shopList.value?.items?.sortedBy { it.price.priceWithDiscount.toInt() }
                _shopList.value?.items= temp_sl as MutableList<Item>
            }
            }
            _needUpdateShopList.value = Unit
    }

    fun changeFavoriteShopItem(application:Application, shopItem: Item){
        viewModelScope.launch() {
            val userRepository = UserRepositoryImpl(application)

            ChangeFavoritesUseCase(userRepository).changeFavorites(shopItem.id, !shopItem.isFavorite)
            shopItem.isFavorite = !shopItem.isFavorite
            _needUpdateShopList.value = Unit
        }
    }
}