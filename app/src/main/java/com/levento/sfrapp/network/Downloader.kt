package com.levento.sfrapp.network

import TAG
import android.util.Log
import com.levento.sfrapp.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection

class Downloader(var urlAddress: String = "https://smaforetagarna.se/nyheter/feed/") {

    suspend fun downloadData(): ArrayList<Article> {

        var articles = arrayListOf<Article>()

        withContext(Dispatchers.IO) {
            Log.d(TAG, "Försöker skapa connection")
            try {
                val connection: Any = Connector.connect(urlAddress)
                val con = connection as HttpURLConnection
                val data = BufferedInputStream(con.inputStream)
                articles = RSSParser(data as InputStream).parseRSS()
            } catch (e: Exception) {
                Log.d(TAG, "Kunde inte skapa connection, " + e.message.toString())
            }

        }
        Log.d(TAG, "downloadData skickar med ${articles.size} stycken artiklar")
        return articles
    }
}