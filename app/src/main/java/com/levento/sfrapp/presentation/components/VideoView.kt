package com.levento.sfrapp.presentation.components

import android.content.Context
import android.graphics.Color
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.levento.sfrapp.domain.util.VideoHtmlParser

@Composable
fun VideoView(videoURL: String, context: Context = LocalContext.current, modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val htmlContent = VideoHtmlParser().parseHtml(videoURL ?: "", screenWidth.value)

    AndroidView(factory = {
        WebView(context).apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return true
                }
            }
            settings.javaScriptEnabled = true
            requestFocus(View.FOCUS_DOWN)
            setBackgroundColor(Color.argb(100, 0, 0, 0))
        }
    },
        update = {
            it.loadData(
                htmlContent,
                "text/html", "UTF-8"
            )
        },
        modifier = modifier
    )
}