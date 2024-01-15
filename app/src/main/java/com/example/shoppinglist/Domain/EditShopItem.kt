package com.example.shoppinglist.Domain

class EditShopItem(private val ShopListRepository:ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
    ShopListRepository.editShopItem(shopItem)
    }
}