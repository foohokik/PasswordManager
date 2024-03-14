package com.example.passwordmanager.presentation.show_websites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.security.crypto.MasterKey
import com.example.passwordmanager.core.KEY
import com.example.passwordmanager.core.SingleLiveData
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.data.WebsiteRepository
import com.example.passwordmanager.presentation.show_websites.adapter.WebsiteListener
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebsitesListViewModel @Inject constructor(
    private val websiteRepository:WebsiteRepository
): ViewModel(), WebsiteListener {


    private val _websitesLiveData = MutableLiveData<List<Website>>()
    val websitesLiveData: LiveData<List<Website>> = _websitesLiveData

    private val _sideEffectLiveData = SingleLiveData<Website>()
    val sideEffectLiveData: LiveData<Website> = _sideEffectLiveData

    init {

       getWebsites (KEY)

    }

    fun getWebsites (masterKey: String) {

        _websitesLiveData.value = websiteRepository.getWebsites(masterKey)

    }


//    fun onAddWebsiteToSharPrefs(name: String, url: String, password: String, icon: String) {
//        val newWebsite = Website(name, url, password, icon)
//        val jsonString = gson.toJson(newWebsite)
//        websiteRepository.saveWebsite(url, jsonString)
//    }
//
//    fun onAddWebsite(name: String, url: String, password: String, icon: String) {
//        val newWebsites = mutableListOf<Website>()
//        websitesLiveData.value?.let { newWebsites.addAll(it) }
//        _websitesLiveData.value = newWebsites
//    }

//    fun onEditContact(id:Int, name: String, surname: String, number: String, position: Int) {
//        val newContact = Contact(id, name, surname, number)
//        val newPeople = mutableListOf<Contact>()
//        peopleLiveData.value?.let { newPeople.addAll(it) }
//        newPeople.removeAt(position)
//        newPeople.add(position, newContact)
//        _peopleLiveData.value = newPeople
//    }

    override fun onClickWebsite(website: Website) {
        _sideEffectLiveData.value = website
    }



}