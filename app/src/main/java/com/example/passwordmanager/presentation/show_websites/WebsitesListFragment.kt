package com.example.passwordmanager.presentation.show_websites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordmanager.R
import com.example.passwordmanager.data.Website
import com.example.passwordmanager.databinding.FragmentWebsitesListBinding
import com.example.passwordmanager.presentation.show_websites.adapter.WebSitesAdapter
import com.example.passwordmanager.presentation.website.WebsiteDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebsitesListFragment : Fragment() {

    private var _binding: FragmentWebsitesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WebSitesAdapter

    private val viewModel: WebsitesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebsitesListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
        binding.btnAdd.setOnClickListener {
            Log.d("TRANS", "BTN")
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, WebsiteDetailFragment.newInstance(Website("","","","")))
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        val manager = LinearLayoutManager(requireContext())
        adapter = WebSitesAdapter(viewModel)
        binding.rvWebsites.layoutManager = manager
        binding.rvWebsites.adapter = adapter
    }


    private fun observe() {

        val listObserver = Observer<List<Website>> { items ->
            adapter.setItems(items)
        }


        val sideEffectObserver = Observer<Website> {

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, WebsiteDetailFragment.newInstance(it))
                .addToBackStack(null)
                .commit()

        }

        viewModel.websitesLiveData.observe(viewLifecycleOwner, listObserver)
        viewModel.sideEffectLiveData.observe(viewLifecycleOwner, sideEffectObserver)

    }

}