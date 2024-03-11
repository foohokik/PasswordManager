package com.example.passwordmanager.presentation.show_websites.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.passwordmanager.R
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.databinding.WebsiteItemBinding

class WebSitesViewHolder(val binding: WebsiteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(website: Website, listener: WebsiteListener) = with(binding) {

        Glide.with(ivIcon.context).load(website.icon).circleCrop()
            .placeholder(R.drawable.baseline_block_24).error(R.drawable.baseline_block_24)
            .into(ivIcon)

        tvUrl.text = website.url
        tvWebsiteName.text = website.name
        root.setOnClickListener {
            listener.onClickWebsite(website)
        }

    }
}