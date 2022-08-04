package com.levento.sfrapp.models

import coil.request.ImageResult

data class Benefit(
    var id: String = PlaceHolders.id.toString(),
    var title: String? = "Titel Saknas",
    var dealtext1: String = "Förmånstitel",
    var dealtext2: String = "Förmånsundertitel",
    var description: String? = "Beskrivning Saknas",
    var imageURL: String? = null,
    var content: String? = "Text Saknas",
    var link: String? = "Länk Saknas",
    var category: MutableList<String>? = mutableListOf(),
    var image: ImageResult? = null
)



