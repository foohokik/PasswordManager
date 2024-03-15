package com.example.passwordmanager.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class WebsiteRepository @Inject constructor (
    private val gson: Gson,
    private val service: WebsiteService,
    private val encryptedSharedPrefs: SharedPreferences) {



    fun saveWebsite (masterKey: String, website: Website) {

        service.setWebsite(website)
        val json = gson.toJson(service.getWebsites())
        encryptedSharedPrefs.edit()
            .putString(masterKey,json)
            .apply()

    }


    fun getWebsites (masterKey: String): List<Website> {

        val value = encryptedSharedPrefs.getString(masterKey,"[]")
        val type: Type = object : TypeToken<ArrayList<Website?>?>() {}.type
        return gson.fromJson(value, type)

    }



}