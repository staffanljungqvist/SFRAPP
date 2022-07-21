package com.levento.sfrapp.domain

import java.util.*

data class Article(
    var id: String = UUID.randomUUID().toString(),
    var title: String? = "Titel Saknas",
    var description: String? = "Beskrivning Saknas",
    var date: String? = "Datum Saknas",
    var imageUrl: String? = null,
    var content: String? = "Text Saknas",
    var link: String? = "Länk Saknas"
)