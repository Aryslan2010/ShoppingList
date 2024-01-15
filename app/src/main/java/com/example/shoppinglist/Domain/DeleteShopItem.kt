package com.example.shoppinglist.Domain

class DeleteShopItem(private val ShopListRepository:ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        ShopListRepository.deleteShopItem(shopItem)
    }
}