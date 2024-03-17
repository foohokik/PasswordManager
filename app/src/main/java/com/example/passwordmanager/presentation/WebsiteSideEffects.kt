package com.example.passwordmanager.presentation

sealed interface WebsiteSideEffects

    object CancelAddWebsite : WebsiteSideEffects
    object NoNameAndUrlAddToWebsite : WebsiteSideEffects
    object BackToWebsites : WebsiteSideEffects
    object EditWebsite : WebsiteSideEffects
