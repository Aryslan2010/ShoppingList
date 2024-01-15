package com.example.shoppinglist.Domain

class GetShopItem(private val ShopListRepository:ShopListRepository) {
    fun getShopItem(shopItemId: Int): ShopItem{
        return ShopListRepository.getShopItem(shopItemId)
    }
}