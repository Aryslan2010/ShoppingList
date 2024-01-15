package com.example.shoppinglist.Domain

class AddShopItem(private val ShopListRepository:ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        ShopListRepository.addShopItem(shopItem)
    }
}