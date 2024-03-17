package com.example.passwordmanager.data

import android.content.SharedPreferences
import com.example.passwordmanager.data.model.Website
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class WebsiteRepository @Inject constructor(
    private val gson: Gson,
    private val encryptedSharedPrefs: SharedPreferences
) {

    fun saveWebsite(masterKey: String, website: Website) {
        val updateList = listOf<Website>(website).plus(getWebsites(masterKey))
        val json = gson.toJson(updateList)
        encryptedSharedPrefs.edit().putString(masterKey, json).apply()
    }

    fun updateWebsites(masterKey: String, listWebsites: List<Website>) {
        val json = gson.toJson(listWebsites)
        encryptedSharedPrefs.edit().putString(masterKey, json).apply()
    }

    fun getWebsites(masterKey: String): List<Website> {
        val value = encryptedSharedPrefs.getString(masterKey, null)
        val type: Type = object : TypeToken<List<Website?>?>() {}.type
        return gson.fromJson<List<Website>?>(value, type).orEmpty()
    }

}