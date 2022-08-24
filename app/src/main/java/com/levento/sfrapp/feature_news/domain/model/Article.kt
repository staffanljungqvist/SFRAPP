package com.levento.sfrapp.feature_news.domain.model

import java.util.*

data class Article(
    var id: String = UUID.randomUUID().toString(),
    var title: String? = "Titel Saknas",
    var description: String? = "Beskrivning Saknas",
    var date: String? = "Datum Saknas",
    var imageUrl: String? = null,
    var videoUrl: String? = null,
    var content: String? = "Text Saknas",
    var link: String? = "Länk Saknas",
    var tags: MutableList<String> = mutableListOf<String>()
) {
    companion object {
       val placeholderList = listOf(
           Article("", "Titel på artikel", "Det här är beskrivning av artikeln", "tue 04/03", "", "", "Det här är content av texten", "", mutableListOf("Nyheter", "Vi möter")),
           Article("", "Titel på artikel", "Det här är beskrivning av artikeln", "tue 04/03", "", "", "Det här är content av texten", "", mutableListOf("Nyheter", "Vi möter") ),
           Article("", "Titel på artikel", "Det här är beskrivning av artikeln", "tue 04/03", "", "", "Det här är content av texten", "", mutableListOf("Nyheter", "Vi möter") ),
           Article("", "Titel på artikel", "Det här är beskrivning av artikeln", "tue 04/03", "", "", "Det här är content av texten", "", mutableListOf("Nyheter", "Vi möter") )
       )
    }
}