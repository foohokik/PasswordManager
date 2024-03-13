package com.example.passwordmanager.data

import android.content.SharedPreferences
import javax.inject.Inject

class WebsiteRepository @Inject constructor (
    private val service: WebsiteService,
    private val encryptedSharedPrefs: SharedPreferences) {

    fun getWebsites() = service.getWebsites()

    fun savePassword (name: String, password: String) = encryptedSharedPrefs.edit()
                            .putString(name,password)
                            .apply()


    fun getPassword (name: String, password: String) = encryptedSharedPrefs.getString(name, password)


}