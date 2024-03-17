package com.example.passwordmanager.presentation

import android.os.Build
import androidx.biometric.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.passwordmanager.BiometricPromptUtils
import com.example.passwordmanager.databinding.ActivityMainBinding
import com.example.passwordmanager.presentation.show_websites.WebsitesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var biometricPrompt: BiometricPrompt
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,
            Build.VERSION.SDK_INT < Build.VERSION_CODES.R
        )
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        biometricPrompt = BiometricPromptUtils.createBiometricPrompt(this) {
            savedInstanceState ?: initFragment()
        }
        biometricPrompt.authenticate(BiometricPromptUtils.createPromptInfo(this))
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                binding.fragmentContainerView.id,
                WebsitesListFragment()
            )
            .commit()
    }
}