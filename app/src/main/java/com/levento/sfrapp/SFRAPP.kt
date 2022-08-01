package com.levento.sfrapp

import android.app.Application
import com.levento.sfrapp.repository.BenefitsRepository
import com.levento.sfrapp.repository.NewsRepository

class SFRAPP : Application() {

    val newsRepository by lazy { NewsRepository() }
    val benefitRepository by lazy { BenefitsRepository() }

}