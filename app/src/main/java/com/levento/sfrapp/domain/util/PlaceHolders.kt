package com.levento.sfrapp.domain.util

import com.levento.sfrapp.R
import com.levento.sfrapp.domain.model.Article

object PlaceHolders {
    val categoryImage = R.drawable.fordon_kategori
    val newsArticleImage = R.drawable.mala1

    val newsList = listOf<Article>(
        Article(
            id = "1",
            title = "Det här är en placeholder nyhetstext, Hur lång kan den vara?",
            content = "<![CDATA[\n" +
                    "<p>Småföretagarnas Riksförbund och <a href=\"https://bit.ly/Skåne_ReLoad\" target=\"_blank\" rel=\"noreferrer noopener\">Affärskompetens</a> anordnade för första gången en nätverksträff för de företagare som deltar i EU-projektet ReLoad Skåne. I ett sommarhett Törringelund presenteras Sonny Jerlström som driver <a href=\"https://www.jerlstromssnickeri.se/\" target=\"_blank\" rel=\"noreferrer noopener\">speciallösningar inom snickeri</a> och tillika <a href=\"https://smaforetagarna.se/forman/medlemskap-online/\" target=\"_blank\" rel=\"noreferrer noopener\">medlem i Småföretagarnas Riksförbund</a>. Han har under en tid varit i en utvecklingsfas för sitt företag och behövt hantera personal och personalfrågor. </p>"
        ),
        Article(
            id = "2",
            title = "Det här är en placeholder nyhetstext",
        ),
        Article(
            "3",
            "Det här är en placeholder nyhetstext",
        )
    )







    var id = 1
        get() {
            field++
            return field
        }
}