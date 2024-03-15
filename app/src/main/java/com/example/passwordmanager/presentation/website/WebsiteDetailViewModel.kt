package com.example.passwordmanager.presentation.website

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.core.SingleLiveData
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.data.WebsiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebsiteDetailViewModel @Inject constructor(
    private val websiteRepository: WebsiteRepository,
    /*private val savedStateHandle: SavedStateHandle*/
) : ViewModel() {

    private val _websiteLiveData = MutableLiveData<Website>()
    val websitesLiveData: LiveData<Website> = _websiteLiveData

    private val _sideEffectLiveData = SingleLiveData<Website>()
    val sideEffectLiveData: LiveData<Website> = _sideEffectLiveData



    fun onAddWebsiteToSharPrefs(name: String, url: String, password: String) {
        val icon = "$url/favicon.ico"
        val newWebsite = Website(name, url, password, icon)
        websiteRepository.saveWebsite(url, newWebsite)
    }



}