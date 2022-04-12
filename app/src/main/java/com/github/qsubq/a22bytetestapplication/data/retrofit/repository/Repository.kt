package com.github.qsubq.a22bytetestapplication.data.retrofit.repository

import com.github.qsubq.a22bytetestapplication.data.retrofit.RetrofitInstance
import com.github.qsubq.a22bytetestapplication.model.NewsModel
import retrofit2.Response


class Repository {

    suspend fun getNews() : Response<NewsModel> {
        return RetrofitInstance.api.getNews()
    }
}