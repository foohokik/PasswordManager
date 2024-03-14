package com.example.passwordmanager.presentation.website

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.data.WebsiteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebsiteDetailViewModel @Inject constructor(
    private val websiteRepository: WebsiteRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {




}