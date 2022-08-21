package com.levento.sfrapp.screens.screencomponents

import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.levento.sfrapp.utils.HtmlImageParser
import com.levento.sfrapp.utils.WebParser


@Composable
fun HTMLContentView(htmlText: String, context: Context = LocalContext.current) {

    Box(
        Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 150.dp, top = 20.dp)
            .fillMaxHeight()
    ) {

        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    movementMethod = LinkMovementMethod.getInstance()
                    this.textSize = 16f
                }
            },
            update = {
                HtmlImageParser(context).displayHtml(htmlText, it)
            }
        )
    }
}

@Composable
fun HTMLContentView2(htmlText: String, context: Context = LocalContext.current) {

    val htmlContent by remember { mutableStateOf(WebParser().parseHtml(htmlText).html()) }

    Box(
        Modifier
            .fillMaxHeight()
            .padding(start = 20.dp, end = 20.dp, bottom = 150.dp, top = 20.dp)
    ) {

        AndroidView(factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                settings.loadsImagesAutomatically = true
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.mediaPlaybackRequiresUserGesture = false
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                requestFocus(View.FOCUS_DOWN)
                setBackgroundColor(Color.argb(0, 0, 0, 0))
            }
        },
        update = {
            it.loadData("<font color=\"" + "666666" + "\">" + htmlContent + "</font>", "text/html", "UTF-8")
        })
    }
}

