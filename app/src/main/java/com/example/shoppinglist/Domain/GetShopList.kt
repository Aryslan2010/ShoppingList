package com.example.shoppinglist.Domain

import androidx.lifecycle.LiveData

class GetShopList(private val ShopListRepository:ShopListRepository) {
    fun getshop(): LiveData<List<ShopItem>>{
        return ShopListRepository.getshop()
    }
}