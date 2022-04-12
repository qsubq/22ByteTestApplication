package com.github.qsubq.a22bytetestapplication.model

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)