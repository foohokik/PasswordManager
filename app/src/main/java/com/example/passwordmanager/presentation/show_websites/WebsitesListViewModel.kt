package com.example.passwordmanager.presentation.show_websites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.core.KEY
import com.example.passwordmanager.core.SingleLiveData
import com.example.passwordmanager.data.model.Website
import com.example.passwordmanager.data.WebsiteRepository
import com.example.passwordmanager.presentation.show_websites.adapter.WebsiteListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebsitesListViewModel @Inject constructor(
    private val websiteRepository: WebsiteRepository
) : ViewModel(), WebsiteListener {

    private val _websitesLiveData = MutableLiveData<List<Website>>()
    val websitesLiveData: LiveData<List<Website>> = _websitesLiveData

    private val _sideEffectLiveData = SingleLiveData<Website>()
    val sideEffectLiveData: LiveData<Website> = _sideEffectLiveData
    init {
        getWebsites()
    }
    private fun getWebsites() {
        _websitesLiveData.value = websiteRepository.getWebsites(KEY)
    }
    override fun onClickWebsite(website: Website) {
        _sideEffectLiveData.value = website
    }
}