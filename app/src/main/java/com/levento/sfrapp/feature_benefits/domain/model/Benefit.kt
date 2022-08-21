package com.levento.sfrapp.feature_benefits.domain.model

import coil.request.ImageResult
import com.levento.sfrapp.common.PlaceHolders

data class Benefit(
    var id: String = PlaceHolders.id.toString(),
    var title: String? = "Titel Saknas",
    var dealtext1: String = "Förmånstitel",
    var dealtext2: String = "Förmånsundertitel",
    var description: String? = "Beskrivning Saknas",
    var imageURL: String? = null,
    var content: String? = "Text Saknas",
    var link: String? = "Länk Saknas",
    var accessLink: String? = "https://www.google.com/",
    var category: MutableList<String>? = mutableListOf(),
    var image: ImageResult? = null
) {
    companion object {

        val placeholder = Benefit(
            id = "1",
            title = "Förmånstitel",
            dealtext1 = "Kampanjerbjudande",
            dealtext2 = "Mer information",
            description = "Det här är beskrivningen av förmånen",
            category = mutableListOf("Aktuellt", "Administration")
        )

        val placeholderList = listOf<Benefit>(
            Benefit(
                id = "1",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                description = "Det här är beskrivningen av förmånen",
                category = mutableListOf("Aktuellt", "Administration")
            ),
            Benefit(
                id = "2",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                description = "Det här är beskrivningen av förmånen",
                category = mutableListOf("Aktuellt", "Fordon")
            ),
            Benefit(
                id = "3",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                description = "Det här är beskrivningen av förmånen",
                category = mutableListOf("Elektronik", "Administration")
            ),
            Benefit(
                id = "4",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                description = "Det här är beskrivningen av förmånen",
                category = mutableListOf("Aktuellt", "Administration")
            ),
        )
    }
}



