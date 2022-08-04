package com.levento.sfrapp.screens.screencomponents

import android.content.Context
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.levento.sfrapp.helpers.ImageLoader

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
                ImageLoader().displayHtml(htmlText, context, it)
            }
        )
    }
}