package com.github.qsubq.a22bytetestapplication.screen.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.a22bytetestapplication.R
import com.github.qsubq.a22bytetestapplication.databinding.ItemNewsLayoutBinding
import com.github.qsubq.a22bytetestapplication.model.Article
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ItemNewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var newsList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        (holder.binding as ItemNewsLayoutBinding).apply {
            tvTitleItem.text = newsList[position].title
            tvDescriptionItem.text = newsList[position].description
            Picasso.get().load(newsList[position].urlToImage).into(imgItem)
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsList(list : List<Article>){
        newsList = list
        notifyDataSetChanged()
    }
}