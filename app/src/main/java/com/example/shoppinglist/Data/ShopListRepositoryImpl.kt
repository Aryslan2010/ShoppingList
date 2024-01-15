package com.example.shoppinglist.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.Domain.ShopItem
import com.example.shoppinglist.Domain.ShopListRepository
import java.util.Random
import java.util.TreeSet

object ShopListRepositoryImpl: ShopListRepository {
    private var autoIncrementedId = 0
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList: TreeSet<ShopItem> = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id)})
    init{
        for(i in 0 until 1000){
            val item = ShopItem("Name $i",i,kotlin.random.Random.nextBoolean())
            addShopItem(item)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementedId++
        }

        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getshop(): LiveData<List<ShopItem>> {
        return shopListLD
    }
    private fun updateList(){
        shopListLD.value = shopList.toList()
    }

}