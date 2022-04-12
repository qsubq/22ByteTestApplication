package com.github.qsubq.a22bytetestapplication.screen.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.qsubq.a22bytetestapplication.data.retrofit.repository.Repository
import com.github.qsubq.a22bytetestapplication.model.NewsModel
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val repo = Repository()
    var newsList : MutableLiveData<Response<NewsModel>> = MutableLiveData()

    fun getNews(){
        viewModelScope.launch {
            newsList.value = repo.getNews()
        }
    }
}