package com.example.passwordmanager.presentation.website

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.passwordmanager.R
import com.example.passwordmanager.databinding.FragmentWebsiteDetailBinding
import com.example.passwordmanager.databinding.FragmentWebsitesListBinding
import com.example.passwordmanager.presentation.show_websites.WebsitesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebsiteDetailFragment : Fragment() {

    private var _binding: FragmentWebsiteDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WebsiteDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebsiteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}