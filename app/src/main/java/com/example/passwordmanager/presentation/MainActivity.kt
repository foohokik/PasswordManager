package com.example.passwordmanager.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passwordmanager.R
import com.example.passwordmanager.databinding.ActivityMainBinding
import com.example.passwordmanager.presentation.show_websites.WebsitesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        savedInstanceState ?: initFragment()
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