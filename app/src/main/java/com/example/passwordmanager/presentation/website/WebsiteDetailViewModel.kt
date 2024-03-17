package com.example.passwordmanager.presentation.website

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.core.KEY
import com.example.passwordmanager.core.SingleLiveData
import com.example.passwordmanager.core.WEB_ARG
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.data.WebsiteRepository
import com.example.passwordmanager.presentation.BackToWebsites
import com.example.passwordmanager.presentation.CancelAddWebsite
import com.example.passwordmanager.presentation.NoNameAndUrlAddToWebsite
import com.example.passwordmanager.presentation.WebsiteSideEffects
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebsiteDetailViewModel @Inject constructor(
    private val websiteRepository: WebsiteRepository,
    private val gson: Gson,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _contentScreen = MutableLiveData<Website>()
    val contentScreen: LiveData<Website> = _contentScreen

    private val _sideEffectLiveData = SingleLiveData<WebsiteSideEffects>()
    val sideEffectLiveData: LiveData<WebsiteSideEffects> = _sideEffectLiveData

    private var website: Website? = null

   // by lazy { savedStateHandle[WEB_ARG] }

    init {
        website = savedStateHandle[WEB_ARG]
        Log.d("NULL", "website  =  " + website)
        setContentScreen()
    }

    private fun setContentScreen() {

        if (website==null) {
            _contentScreen.value = contentScreen.value?.copy(
                name = "",
                url = "",
                password = "",
                icon = ""
            )
        } else {

                  website?.let { _contentScreen.value = it }

            Log.d("NULL", "contentState  =  " + _contentScreen.value)
        }


    }



    fun onAddOrEditWebsiteToSharPrefs(url: String, name: String, password: String) {
        val icon = "$url/favicon.ico"
        val newWebsite = Website(url, name, password, icon)

        if (website == null) {
            Log.d("NULL", "true")
            if (name.isBlank() && url.isBlank()) {
                _sideEffectLiveData.value = NoNameAndUrlAddToWebsite

            } else {

                websiteRepository.saveWebsite(KEY, newWebsite)
                _sideEffectLiveData.value = BackToWebsites

            }

        } else {

            onEditWebsite(newWebsite)

        }

    }


    fun onEditWebsite(newWebsite: Website) {
        val icon = "${newWebsite?.url}/favicon.ico"
        val updateListWebsites = websiteRepository.getWebsites(KEY).toMutableList()
        val position = updateListWebsites.indexOf(website)
        updateListWebsites[position] = newWebsite
        websiteRepository.updateWebsites(KEY, updateListWebsites)
        _sideEffectLiveData.value = BackToWebsites

    }

    fun onCancel() {

        _sideEffectLiveData.value = CancelAddWebsite

    }


}