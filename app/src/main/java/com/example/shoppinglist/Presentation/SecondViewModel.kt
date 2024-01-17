package com.example.shoppinglist.Presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.ShopListRepositoryImpl
import com.example.shoppinglist.Domain.AddShopItem
import com.example.shoppinglist.Domain.EditShopItem
import com.example.shoppinglist.Domain.GetShopItem
import com.example.shoppinglist.Domain.ShopItem

class SecondViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItem = GetShopItem(repository)
    private val addShopItem = AddShopItem(repository)
    private val editShopItem = EditShopItem(repository)
    private val _errorInputName = MutableLiveData<Boolean>()
    private val _errorInputCount = MutableLiveData<Boolean>()
    private val _shouldCloseScreen = MutableLiveData<Unit>()

    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem
    fun getShopItem(shopItemId: Int){
        val item  = getShopItem.getShopItem(shopItemId)
        _shopItem.value = item
    }
    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid){
            val shopItem = ShopItem(name,count,true)
            addShopItem.addShopItem(shopItem)
            finishWork()
        }

    }
    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid){
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItem.editShopItem(item)
                finishWork() }

        }

    }
    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }
    private fun parseCount(inputCount: String?): Int{
        return try {
            inputCount?.trim()?.toInt()?: 0
        } catch (e: Exception){0}
    }
    private fun validateInput(name:String,count:Int): Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (count<= 0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }
    public fun resetErrorInputName(){
        _errorInputName.value = false
    }
    public fun resetErrorInputCount(){
        _errorInputCount.value = false
    }
    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}