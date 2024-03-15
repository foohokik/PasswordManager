package com.example.passwordmanager.presentation.website

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.passwordmanager.R
import com.example.passwordmanager.core.WEB_ARG
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.databinding.FragmentWebsiteDetailBinding
import com.example.passwordmanager.presentation.show_websites.WebsitesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebsiteDetailFragment : Fragment() {

    private var _binding: FragmentWebsiteDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WebsiteDetailViewModel by viewModels()
    private val website: Website? by lazy { requireArguments().get(WEB_ARG) as? Website }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebsiteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TRANS", "FR")
        initView()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initView() {
        with(binding) {

            editName.setText(website?.name)
            editUrl.setText(website?.url)
            editPass.setText(website?.password)

        }
    }


    private fun initListeners() {
        with(binding) {
            btnCancel.setOnClickListener {

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, WebsitesListFragment())
                    .addToBackStack(null)
                    .commit()

            }

            btnSave.setOnClickListener {


                val name = editName.text.toString()
                val url = editUrl.text.toString()
                val password = editPass.text.toString()

                viewModel.onAddWebsiteToSharPrefs(
                    name = name,
                    url = url,
                    password = password
                )

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, WebsitesListFragment())
                    .addToBackStack(null)
                    .commit()

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