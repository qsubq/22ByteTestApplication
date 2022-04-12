package com.github.qsubq.a22bytetestapplication.data.retrofit.api

import com.github.qsubq.a22bytetestapplication.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/top-headlines?country=ru&apiKey=2d694a43c61f4b69aff1829620ccb104")
    suspend fun getNews() : Response<NewsModel>
}