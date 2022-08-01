package com.levento.sfrapp.screens.articledetail

import TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleDetailViewModel: ViewModel() {

    var article = mutableStateOf(Article())

    private val newsRepository: NewsRepository = NewsRepository()


    fun getArticle(title: String) {
        viewModelScope.launch {
            Log.d(TAG, "Försöker hämta en artikel med title: " + title)
            val selectedArticle = newsRepository.getArticle(title)
            selectedArticle?.let { newsArticle ->
                article.value = newsArticle
            }
        }
    }
}