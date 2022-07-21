package com.levento.sfrapp.repository

import com.levento.sfrapp.domain.Article
import com.levento.sfrapp.domain.PlaceHolders
import com.levento.sfrapp.network.Downloader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor() {

    val urlAddress = "https://smaforetagarna.se/nyheter/feed/"

    suspend fun getNews(): List<Article> {
        val result = Downloader(urlAddress).downloadData()
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


val placeHolderNews = listOf<Article>()