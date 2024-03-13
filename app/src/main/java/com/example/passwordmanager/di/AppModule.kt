package com.example.passwordmanager.di

import android.content.SharedPreferences
import com.example.passwordmanager.data.EncryptedSharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPrefs (
        encryptedSharedPrefs: EncryptedSharedPreferencesManager
    ):SharedPreferences = encryptedSharedPrefs.getSharedPrefs()


}