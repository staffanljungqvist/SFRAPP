package com.levento.sfrapp.screens.homescreen

import TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.repository.BenefitsRepository
import com.levento.sfrapp.repository.NewsRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val newsRepository: NewsRepository = NewsRepository()
    private val benefitsRepository: BenefitsRepository = BenefitsRepository()

    var newsArticles = mutableStateOf(listOf(Article()))
    var exclusiveBenefits by mutableStateOf(listOf(Benefit()))

    init {
        getNews()
        getBenefits()
    }

    fun getNews() {
        viewModelScope.launch {
            val result = newsRepository.getNews()
            Log.d(TAG, "Hämtade nyheter")
            newsArticles.value = result
        }
    }


    fun getBenefits() {
        viewModelScope.launch {
            val benefitdata = benefitsRepository.getAllBenefitsFromFirestore()

            if (benefitdata.data != null) {
                exclusiveBenefits =
                    extractExclusiveBenefits(benefitdata.data!!) ?: listOf<Benefit>()
            } else {
                Log.d(TAG, "något gick fel")
            }
        }
    }

    fun extractExclusiveBenefits(benefits: List<Benefit>): List<Benefit> {
        val exclusiveResult = mutableListOf<Benefit>()
        for (benefit in benefits) {
            benefit.category?.let { categories ->
                for (category in categories) {
                    if (category == "Aktuella") {
                        exclusiveResult.add(benefit)
                    }
                }
            }
        }
        Log.d(TAG, "Antal exklusiva förmåner: " + exclusiveResult.size)
        return exclusiveResult
    }
}