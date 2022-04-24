package com.github.qsubq.a22bytetestapplication.screen.news

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.qsubq.a22bytetestapplication.R
import com.github.qsubq.a22bytetestapplication.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    private val adapter by lazy {
        NewsAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        if (viewModel.newsList.value == null) {
            getNews()
        }
    }

    private fun init() {
        val recyclerView = binding.rcView
        recyclerView.adapter = adapter

        binding.swipeLayout.setOnRefreshListener {
            getNews()
            binding.swipeLayout.isRefreshing = false
        }
    }

    private fun getNews() {

        if (viewModel.isOnline()) {
            viewModel.getNews()
        } else {
            view?.let {
                Snackbar.make(it, getString(R.string.snackbar_text), 4000)
                    .setAction(getString(R.string.retry_string)) {
                        getNews()
                    }
                    .show()
            }
        }

        viewModel.newsList.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapter.setNewsList(it) }
        }
    }
}