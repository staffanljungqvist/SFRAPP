package com.levento.sfrapp.domain.util

import android.content.Context
import android.widget.TextView
import androidx.core.text.HtmlCompat

class HtmlImageParser(val context: Context) {

    fun displayHtml(html: String, textView: TextView) {

        // Creating object of ImageGetter class you just created
        val imageGetter = ImageGetter(
            textView,
            context
        )

        // Using Html framework to parse html
        val styledText = HtmlCompat.fromHtml(
            html,
            HtmlCompat.FROM_HTML_MODE_LEGACY,
            imageGetter, null
        )

        // to enable image/link clicking
        //     htmlTextView.movementMethod = LinkMovementMethod.getInstance()

        // setting the text after formatting html and downloading and setting images
        textView.text = styledText
    }
}