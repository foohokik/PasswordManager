package com.example.passwordmanager.presentation.show_websites.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.passwordmanager.data.Website

class WebDiffUtil(
    private val oldList: MutableList<Website>,
    private val newList: List<Website>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition] == newList[newItemPosition]

    }
}