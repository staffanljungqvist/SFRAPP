package com.levento.sfrapp.presentation.components

import android.R
import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.levento.sfrapp.TAG
import com.levento.sfrapp.domain.util.HtmlImageParser
import com.levento.sfrapp.domain.util.WebParser


@Composable
fun HTMLContentView(htmlText: String, context: Context = LocalContext.current) {

    Column {

        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    movementMethod = LinkMovementMethod.getInstance()
                    this.textSize = 16f
                }
            },
            update = {
                HtmlImageParser(context).displayHtml(htmlText, it)
            },
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 150.dp,
                top = 20.dp
            )
        )
    }
}


@Composable
fun HTMLContentView2(htmlText: String, context: Context = LocalContext.current) {

    val htmlContent by remember { mutableStateOf(WebParser().parseHtml(htmlText)) }

    Log.d(TAG, htmlContent)
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
               // settings.allowFileAccess = true
              //  scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
              //  settings.javaScriptCanOpenWindowsAutomatically = true
                settings.mediaPlaybackRequiresUserGesture = false
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                requestFocus(View.FOCUS_DOWN)
                setBackgroundColor(Color.argb(0, 0, 0, 0))
            }
        },
            update = {
                it.loadDataWithBaseURL(
                    "file:///android_res/",
                    //"<font color=\"" + "666666" + "\">" + htmlContent + "</font>",
                    htmlContent,
                    "text/html", "UTF-8", ""
                )
            })
    }
}

@Composable
fun HTMLContentView3(url: String?, context: Context = LocalContext.current) {

    var isLoading by remember { mutableStateOf(true) }

    Box(
        Modifier
            .fillMaxHeight()
            .padding(start = 20.dp, end = 20.dp, bottom = 150.dp, top = 20.dp)
    ) {
        AndroidView(factory = {
            WebView(context).apply {
                settings.javaScriptEnabled = true
                visibility = View.INVISIBLE
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        view?.loadUrl(
                            "javascript:(function() { " +
                                    "document.getElementsByClassName('elementor-section-wrap')[0].style.display='none'; " +
                                    "document.querySelectorAll('[data-elementor-type=footer]')[0].style.display='none'; " +
                                    "document.getElementsByClassName('elementor-section elementor-top-section elementor-element elementor-element-9ba81c8 elementor-section-boxed elementor-section-height-default elementor-section-height-default')[0].style.display='none'; " +
                                    "})()"
                        )
                        isLoading = false
                        visibility = View.VISIBLE
                    }
                }
                loadUrl(url ?: "")
            }
        },
            update = {
                //    it.loadUrl(url)
            })
        if (isLoading) {
            Text("Laddar")
        }
    }
}