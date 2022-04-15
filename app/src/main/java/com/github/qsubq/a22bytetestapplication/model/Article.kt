package com.github.qsubq.a22bytetestapplication.model

import androidx.annotation.Nullable

data class Article(
    val author: Any,
    val content: Any,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String = ""
)