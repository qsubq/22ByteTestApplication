package com.github.qsubq.a22bytetestapplication.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.a22bytetestapplication.R
import com.github.qsubq.a22bytetestapplication.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar


class NewsFragment : Fragment() {
    private lateinit var binding : FragmentNewsBinding
    private lateinit var adapter : NewsAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater,container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getNews()
    }

    private fun init() {
        recyclerView = binding.rcView
        adapter = NewsAdapter()
        recyclerView.adapter = adapter

        binding.swipeLayout.setOnRefreshListener {
            getNews()
            binding.swipeLayout.isRefreshing = false
        }
    }

    private fun getNews(){
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        if (viewModel.newsList.value == null){
            if(viewModel.isOnline()){
                viewModel.getNews()
            }
            else{
                view?.let { Snackbar.make(it,getString(R.string.snackbar_text),4000)
                    .setAction(getString(R.string.retry_string), View.OnClickListener {
                        getNews()
                    })
                    .show() }
            }
        }
        viewModel.newsList.observe(viewLifecycleOwner){list ->
            list.body()?. let {adapter.setNewsList(it)}

        }
    }
}