package com.github.qsubq.a22bytetestapplication.screen.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.a22bytetestapplication.databinding.FragmentMainBinding

class NewsFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    private lateinit var adapter : NewsAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        recyclerView = binding.rcView
        adapter = NewsAdapter()
        recyclerView.adapter = adapter
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        if(viewModel.newsList.value == null){
            viewModel.getNews()
        }

        viewModel.newsList.observe(viewLifecycleOwner){list ->
            list.body()?. let {adapter.setNewsList(it)}
        }

        binding.swipeLayout.setOnRefreshListener {
            viewModel.getNews()
            binding.swipeLayout.isRefreshing = false
        }
    }

}