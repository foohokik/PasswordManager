package com.example.passwordmanager.presentation.show_websites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.data.model.Website
import com.example.passwordmanager.databinding.WebsiteItemBinding

class WebSitesAdapter( private val listener: WebsiteListener):RecyclerView.Adapter<WebSitesViewHolder> () {

   private val items = mutableListOf<Website>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebSitesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = WebsiteItemBinding.inflate(inflater, parent, false)
        return WebSitesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: WebSitesViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }
    override fun getItemCount(): Int = items.size
    fun setItems(newItems: List<Website>) {
        val diffResult = DiffUtil.calculateDiff(
            WebDiffUtil(
                items,
                newItems
            )
        )
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}