package com.levento.sfrapp.repository

import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.network.Downloader


class NewsRepository {

    suspend fun getNews(): List<Article> {

        val result = Downloader().downloadData()

        return if (result.isEmpty()) {
            PlaceHolders.newsList
        } else {
            result.toCollection(ArrayList())
        }
    }

    suspend fun getArticle(title: String): Article? {
        val allArticles = getNews()
        return allArticles.find { article -> article.title == title }
    }
}