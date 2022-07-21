package com.levento.sfrapp.screens.homescreen

import TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.domain.Article
import com.levento.sfrapp.domain.Benefit
import com.levento.sfrapp.repository.BenefitsRepository
import com.levento.sfrapp.repository.NewsRepository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val newsRepository: NewsRepository = NewsRepository()
    private val benefitsRepository: BenefitsRepository = BenefitsRepository()

    var newsArticles = mutableStateOf(listOf(Article()))
    val exclusiveBenefits by mutableStateOf(listOf(Benefit()))

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            val result = newsRepository.getNews()
            Log.d(TAG, "Hämtade nyheter")
            newsArticles.value = result
        }
    }
}