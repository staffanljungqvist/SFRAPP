package com.levento.sfrapp.feature_news.domain.util


import android.util.Log
import com.levento.sfrapp.TAG
import com.levento.sfrapp.feature_news.domain.model.Article
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class RSSParser(val inputStream: InputStream) {

    var articles: ArrayList<Article> = ArrayList()

    suspend fun parseRSS(): ArrayList<Article> {
        try {
            Log.d(TAG, "Parsing RSS")
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var event = parser.eventType
            var tagValue: String? = null
            var isSiteMeta = true
            articles.clear()
            var article = Article()
            do {
                val tagName = parser.name
                when (event) {
                    XmlPullParser.START_TAG -> if (tagName.equals("item", ignoreCase = true)) {
                        article = Article()
                        isSiteMeta = false
                    }
                    XmlPullParser.TEXT -> tagValue = parser.text
                    XmlPullParser.END_TAG -> {
                        if (!isSiteMeta) {
                            if (tagName.equals("title", ignoreCase = true)) {
                                article.title = tagValue
                            } else if (tagName.equals("description", ignoreCase = true)) {
                                val desc = tagValue
                                article.description = desc!!.substring(
                                    desc.indexOf("/>") + 1,
                                    endIndex = desc.indexOf("<p class")
                                )
                            } else if (tagName.equals("pubDate", ignoreCase = true)) {
                                article.date = tagValue
                            } else if (tagName.equals("media:thumbnail", ignoreCase = true)) {
                                article.imageUrl = parser.getAttributeValue(0)
                            } else if (tagName.equals("link", ignoreCase = true)) {
                                article.link = tagValue
                            } else if (tagName.equals("content:encoded", ignoreCase = true)) {
                                article.content = tagValue
                            }
                        }
                        if (tagName.equals("item", ignoreCase = true)) {
                            articles.add(article)
                            isSiteMeta = true
                        }
                    }
                }
                event = parser.next()
            } while (event != XmlPullParser.END_DOCUMENT)
            Log.d("SFRDebug", "Parse färdig, antal artiklar: ${articles.size}")
            return articles
        } catch (e: XmlPullParserException) {
            Log.d(TAG, "xmlpUllParserException: " + e.message.toString())
            e.printStackTrace()
        } catch (e: IOException) {
            Log.d(TAG, "IOException: " + e.message.toString())
            e.printStackTrace()
        }
        return arrayListOf<Article>()
    }
}