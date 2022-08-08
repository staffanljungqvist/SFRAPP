package com.levento.sfrapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.levento.sfrapp.data.repository.BenefitsRepositoryImpl
import com.levento.sfrapp.data.repository.NewsRepositoryImpl
import com.levento.sfrapp.data.repository.UserRepositoryImpl

class SFRAPP : Application(), ImageLoaderFactory {

    val newsRepository by lazy { NewsRepositoryImpl() }
    val benefitRepository by lazy { BenefitsRepositoryImpl() }
    val userRepository by lazy { UserRepositoryImpl() }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}