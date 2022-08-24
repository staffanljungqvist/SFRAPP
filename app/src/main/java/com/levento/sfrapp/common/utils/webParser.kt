package com.levento.sfrapp.common.utils

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
    //    val text2 = "<html><style type='text/css'>@font-face { font-family: montserrat_bold; src: url('font/sourcesanspro_regular.otf'); line-gap-override: 10%; } body p {font-family: montserrat_bold;  }</style>" + "<body >" + "<p align=\"justify\" style=\"font-size: 10px; font-family: montserrat_bold;\">" + htmlText + "</p> " + "</body></html>"
     //   val text3 = "<html><style type='text/css'>@font-face { font-family: montserrat_bold; src: url('font/sourcesanspro_regular.otf'); line-gap-override: 10%; } body p {font-family: montserrat_bold;  }</style>" + "<body >" + "<p align=\"justify\" style=\"font-size: 10px; font-family: montserrat_bold;\">" + htmlContent + "</p> " + "</body></html>"
     //   val text4 = "<html><style> body p { line-height: 1.5;} </style>" + "<body >" + "<p>" + htmlContent + "</p> " + "</body></html>"
       // "<style> p { line-height: 0.7;} p.big { line-height: 1.0; } </style>"

    }

}