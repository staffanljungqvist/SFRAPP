package com.levento.sfrapp.repository

import com.levento.sfrapp.models.Article
import com.levento.sfrapp.models.PlaceHolders
import com.levento.sfrapp.network.Downloader
import javax.inject.Inject
import javax.inject.Singleton

class NewsRepository(
) {



    suspend fun getNews(): List<Article> {
        val result = Downloader().downloadData()
        if (result.isEmpty()) {
            return PlaceHolders.newsList
        } else {
            return result.toCollection(ArrayList())
        }
    }

    suspend fun getArticle(title: String): Article? {
        val allArticles = getNews()
        val selectedArticle = allArticles.find {article -> article.title == title }
        return selectedArticle
    }
}