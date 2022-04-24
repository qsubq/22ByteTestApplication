package com.github.qsubq.a22bytetestapplication.screen.news

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.a22bytetestapplication.databinding.ItemNewsLayoutBinding
import com.github.qsubq.a22bytetestapplication.model.Article
import com.github.qsubq.a22bytetestapplication.model.NewsModel
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: ItemNewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var newsList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =
            ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        (holder.binding).apply {
            tvTitleItem.text = newsList[position].title
            tvDescriptionItem.text = newsList[position].description
            Picasso.get().load(newsList[position].urlToImage).into(imgItem)

        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewsList(list: NewsModel) {
        newsList = list.articles
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: NewsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(newsList[holder.adapterPosition].url))
            ContextCompat.startActivity(holder.itemView.context, browserIntent, null)
        }
    }
}