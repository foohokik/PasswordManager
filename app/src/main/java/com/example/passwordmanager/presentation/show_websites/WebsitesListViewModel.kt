package com.example.passwordmanager.presentation.show_websites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.passwordmanager.App
import com.example.passwordmanager.core.SingleLiveData
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.data.WebsiteRepository
import com.example.passwordmanager.presentation.show_websites.adapter.WebsiteListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebsitesListViewModel @Inject constructor(private val websiteRepository:WebsiteRepository): ViewModel(), WebsiteListener {


    private val _websitesLiveData = MutableLiveData<List<Website>>()
    val websitesLiveData: LiveData<List<Website>> = _websitesLiveData

    private val _sideEffectLiveData = SingleLiveData<Website>()
    val sideEffectLiveData: LiveData<Website> = _sideEffectLiveData

    init {
        _websitesLiveData.value = websiteRepository.getWebsites()
    }

    fun onAddContact(name: String, url: String, password: String, icon: String) {
        val newWebsites = mutableListOf<Website>()
        websitesLiveData.value?.let { newWebsites.addAll(it) }
        _websitesLiveData.value = newWebsites
    }

    fun onEditContact(id:Int, name: String, surname: String, number: String, position: Int) {
        val newContact = Contact(id, name, surname, number)
        val newPeople = mutableListOf<Contact>()
        peopleLiveData.value?.let { newPeople.addAll(it) }
        newPeople.removeAt(position)
        newPeople.add(position, newContact)
        _peopleLiveData.value = newPeople
    }

    override fun onClickWebsite(website: Website) {
        TODO("Not yet implemented")
    }


//    companion object {
//
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                val application =
//                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
//
//                return WebsitesListViewModel(
//                    (application as App).websiteRepository
//                ) as T
//            }
//        }
//    }


}