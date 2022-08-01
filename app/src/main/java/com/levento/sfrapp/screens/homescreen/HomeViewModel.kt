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

class HomeViewModel: ViewModel() {


    private val _exclusiveBenefits = mutableStateOf(listOf<Benefit>())
    val exclusiveBenefits = _exclusiveBenefits

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