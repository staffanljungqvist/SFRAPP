package com.levento.sfrapp.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class WebParser {

    fun parseHtml(htmlText: String): Document {

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
        return doc
    }

}