package com.levento.sfrapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.levento.sfrapp.repository.BenefitsRepository
import com.levento.sfrapp.repository.NewsRepository

class SFRAPP : Application(), ImageLoaderFactory {

    val newsRepository by lazy { NewsRepository() }
    val benefitRepository by lazy { BenefitsRepository() }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}