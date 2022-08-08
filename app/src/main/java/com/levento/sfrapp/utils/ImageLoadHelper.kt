package com.levento.sfrapp.utils

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.core.text.HtmlCompat
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import com.levento.sfrapp.models.Benefit
import com.levento.sfrapp.models.BenefitCategory

class ImageLoadHelper(val context: Context) {

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

    //Laddar in bilderna som ska användas till förmånerna i minnet, så att dom inte laddas in medan man scrollar listan.
    suspend fun loadAllImages(benefits: List<Benefit>, categories: List<BenefitCategory>) {
        for (benefit in benefits) {
            benefit.imageURL?.let {
                benefit.image = loadImage(benefit.imageURL!!)
            }
        }
        Log.d("initload", "loadAllImages är färdig med förmånerna")

        //Laddar även in bakgrundsbilderna till kategorierna TODO Ska dessa användas?
        for (category in categories) {
            category.imageURL?.let {
                category.image = loadImage(category.imageURL!!)
            }
        }
        Log.d("initload", "loadAllImages är färdig med kategorierna")
    }

    //Tar emot en url till en bild, och laddar ner den till minnet.
    suspend fun loadImage(imageUrl: String): ImageResult {
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            // Optional, but setting a ViewSizeResolver will conserve memory by limiting the size the image should be preloaded into memory at.
            .build()
        return context.imageLoader.execute(request)
    }

}