package com.levento.sfrapp.data.repository

import android.util.Log
import com.levento.sfrapp.TAG
import com.levento.sfrapp.domain.model.Article
import com.levento.sfrapp.domain.repository.NewsRepository
import com.levento.sfrapp.domain.model.NetworkResponse
import com.levento.sfrapp.domain.util.Connector
import com.levento.sfrapp.domain.util.RSSParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import javax.inject.Inject

const val RSS_URL = "https://smaforetagarna.se/nyheter/feed/"


class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    override suspend fun getNews(): NetworkResponse<List<Article>, Exception> {

        var dataOrException = NetworkResponse<List<Article>, Exception>()
        try {
            dataOrException = downloadData()
        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }

    private suspend fun downloadData(): NetworkResponse<List<Article>, Exception> {

        lateinit var response: NetworkResponse<List<Article>, Exception>

        withContext(Dispatchers.IO) {
            response = try {
                val connection: Any = Connector.connect(RSS_URL)
                val con = connection as HttpURLConnection
                val data = BufferedInputStream(con.inputStream)
                val articles = RSSParser(data as InputStream).parseRSS()
                NetworkResponse(articles)
            } catch (e: Exception) {
                NetworkResponse(e = e)
            }
        }
        return response
    }
}