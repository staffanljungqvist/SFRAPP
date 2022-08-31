package com.levento.sfrapp.domain.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class WebParser {

    fun parseHtml(htmlText: String): String {

        val doc = Jsoup.parse(htmlText)

        for (element in doc.allElements) {
            for (attribute in element.attributes()) {
                if (attribute.key == "width") {
                    attribute.setValue("100%")
                }
                if (attribute.key == "height") {
                    element.removeAttr("height")
                }
            }
        }

        return withStyle(doc.html())
    }

    fun withStyle(htmlText: String): String {
        return  "<html><style> body p { line-height: 1.5;} br { display: block; margin: 100px 0;} </style>" + "<body >" + "<p>" + htmlText + "</p> " + "</body></html>"
    }

}