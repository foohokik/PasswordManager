package com.example.passwordmanager.data

import javax.inject.Inject

class WebsiteService @Inject constructor() {


    private val websitesList = mutableListOf<Website>()


    fun getWebsites(): List<Website> {
        return websitesList
    }

    fun setWebsite(website: Website): List<Website> {

        return  websitesList.plus(website)

    }

}