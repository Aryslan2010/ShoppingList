package com.example.shoppinglist.Presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Domain.ShopItem
import com.example.shoppinglist.R

class ShopListAdapter: ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()) {
companion object {
    const val VIEW_TYPE_ENABLED =100
    const val VIEW_TYPE_DISABLED =200
    const val MAX_POOL_SIZE = 15
}
    var onShopItemLongClickListen: ((ShopItem)-> Unit)? = null
    var onShopItemShortClickListen: ((ShopItem)-> Unit)? = null
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {


        val layout = when(viewType){
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED ->R.layout.item_shop_disabled
            else ->throw RuntimeException("Неизвестный ViewType: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false

        )
        return ShopItemViewHolder(view)
    }



    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        Log.d("ShoplistAdapter", "onBindViewHolder, count: ${++count}")
        val shopItem = getItem(position)
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.itemView.setOnLongClickListener{
            onShopItemLongClickListen?.invoke(shopItem)
            true
        }
        viewHolder.itemView.setOnClickListener {
            onShopItemShortClickListen?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable){
            VIEW_TYPE_ENABLED
        }
        else{
            VIEW_TYPE_DISABLED
        }
    }
    }

