package com.levento.sfrapp.common.utils

import android.util.Log
import org.jsoup.Jsoup

class VideoHtmlParser {
    fun parseHtml(videoURL: String, width: Float): String {
        val height = width / 1.77777
        var htmlString = "<div class=\"wp-block-embed__wrapper\"><div class=\"ast-oembed-container\"><iframe loading=\"lazy\" title=\"\" width=\"" + width + "\" height=\"" + height + "\" src=\"" + videoURL +  "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></div></div>"
        return "<body style=\"margin: 0; padding: 0\">" + htmlString + "</body>"
    }
}