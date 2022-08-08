package com.levento.sfrapp.data.repository

import com.levento.sfrapp.models.Article
import com.levento.sfrapp.data.PlaceHolders
import com.levento.sfrapp.interfaces.iNewsRepository
import com.levento.sfrapp.network.Downloader


class NewsRepositoryImpl: iNewsRepository {

    override suspend fun getNews(): List<Article> {

        val result = Downloader().downloadData()

        return if (result.isEmpty()) {
            PlaceHolders.newsList
        } else {
            result.toCollection(ArrayList())
        }
    }
}