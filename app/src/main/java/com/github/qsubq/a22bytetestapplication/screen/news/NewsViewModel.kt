package com.github.qsubq.a22bytetestapplication.screen.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.qsubq.a22bytetestapplication.data.NetworkHelper
import com.github.qsubq.a22bytetestapplication.data.retrofit.repository.Repository
import com.github.qsubq.a22bytetestapplication.model.NewsModel
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    private val repo = Repository()
    var newsList : MutableLiveData<Response<NewsModel>> = MutableLiveData()


    fun getNews(){
        viewModelScope.launch {
            newsList.value = repo.getNews()
        }
    }

    fun isOnline(): Boolean{
        return NetworkHelper.isOnline(context)
    }
}