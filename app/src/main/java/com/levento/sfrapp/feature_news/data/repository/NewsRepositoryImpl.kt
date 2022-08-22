package com.levento.sfrapp.feature_news.data.repository

import android.util.Log
import com.levento.sfrapp.TAG
import com.levento.sfrapp.feature_news.domain.model.Article
import com.levento.sfrapp.feature_news.domain.repository.NewsRepository
import com.levento.sfrapp.common.domain.model.NetworkResponse
import com.levento.sfrapp.feature_news.domain.util.Connector
import com.levento.sfrapp.feature_news.domain.util.RSSParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor() : NewsRepository {

   // private val downloader = Downloader()
    private val rssUrl = "https://smaforetagarna.se/nyheter/feed/"

    override suspend fun getNews(): NetworkResponse<List<Article>, Exception> {

        val dataOrException = NetworkResponse<List<Article>, Exception>()
        //TODO Implementera BenefitService och anropa getBenefits().
        try {
            dataOrException.data = downloadData()
        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }

    suspend fun downloadData(): ArrayList<Article> {

        var articles = arrayListOf<Article>()

        withContext(Dispatchers.IO) {
            Log.d(TAG, "Försöker skapa connection")
            try {
                val connection: Any = Connector.connect(rssUrl)
                val con = connection as HttpURLConnection
                val data = BufferedInputStream(con.inputStream)
                articles = RSSParser(data as InputStream).parseRSS()
                Log.d(TAG, "So far so good")
            } catch (e: Exception) {
                Log.d(TAG, "Kunde inte skapa connection, " + e.message.toString())
            }

        }
        Log.d(TAG, "downloadData skickar med ${articles.size} stycken artiklar")
        return articles
    }
}