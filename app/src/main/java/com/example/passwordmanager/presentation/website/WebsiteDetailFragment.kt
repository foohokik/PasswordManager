package com.example.passwordmanager.presentation.website

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.passwordmanager.R
import com.example.passwordmanager.core.WEB_ARG
import com.example.passwordmanager.data.model.Website
import com.example.passwordmanager.databinding.FragmentWebsiteDetailBinding
import com.example.passwordmanager.presentation.BackToWebsites
import com.example.passwordmanager.presentation.CancelAddWebsite
import com.example.passwordmanager.presentation.NoNameAndUrlAddToWebsite
import com.example.passwordmanager.presentation.WebsiteSideEffects
import com.example.passwordmanager.presentation.show_websites.WebsitesListFragment
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        observe()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun observe() {
        val sideEffectObserver = Observer<WebsiteSideEffects> { action ->
            when (action) {
                is BackToWebsites -> {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, WebsitesListFragment())
                        .addToBackStack(null)
                        .commit()
                }
                is NoNameAndUrlAddToWebsite -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.website_detail_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CancelAddWebsite -> {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, WebsitesListFragment())
                        .addToBackStack(null)
                        .commit()
                }
                else ->{}
            }
        }

        val screenStateObserver = Observer<Website?>{
            initView(it)
        }

        viewModel.sideEffectLiveData.observe(viewLifecycleOwner, sideEffectObserver)
         viewModel.contentScreen.observe(viewLifecycleOwner, screenStateObserver)
    }

   private fun initView(website: Website?) {
        with(binding) {
            editName.setText(website?.name)
            editUrl.setText(website?.url)
            editPass.setText(website?.password)
        }
    }

    private fun initListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                viewModel.onCancel()
            }
            btnSave.setOnClickListener {
                val name = editName.text.toString()
                val url = editUrl.text.toString()
                val password = editPass.text.toString()

                viewModel.onAddOrEditWebsiteToSharPrefs(
                        name = name,
                        url = url,
                        password = password
                    )
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(website: Website?) =
            WebsiteDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(WEB_ARG, website)
                }
            }
    }


}