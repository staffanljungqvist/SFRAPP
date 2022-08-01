package com.levento.sfrapp.screens.screencomponents

import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun HTMLContentView(htmlText: String) {

    Box(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 150.dp, top = 20.dp).fillMaxHeight()) {
        val htmlDescription = remember(htmlText) {
            HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    //   movementMethod = LinkMovementMethod.getInstance()
                    this.textSize = 16f
                }
            },
            update = {
                it.text = htmlDescription
            }
        )
    }
}