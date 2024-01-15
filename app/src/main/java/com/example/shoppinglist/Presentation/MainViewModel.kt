package com.example.shoppinglist.Presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.ShopListRepositoryImpl
import com.example.shoppinglist.Domain.DeleteShopItem
import com.example.shoppinglist.Domain.EditShopItem
import com.example.shoppinglist.Domain.GetShopList
import com.example.shoppinglist.Domain.ShopItem

class MainViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopList = GetShopList(repository)
    private val deleteShopItem = DeleteShopItem(repository)
    private val editShopItem = EditShopItem(repository)
    val shopList = getShopList.getshop()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItem.deleteShopItem(shopItem)
        }
    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItem.editShopItem(newItem)
    }
    }
