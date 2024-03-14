package com.example.passwordmanager.data

import java.io.Serializable

data class Website(
    val url: String,
    val name: String,
    val password: String,
    val icon: String
) : Serializable
