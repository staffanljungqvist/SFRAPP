package com.levento.sfrapp.domain.model

import coil.request.ImageResult
import com.levento.sfrapp.domain.util.PlaceHolders

data class Benefit(
    var id: String = PlaceHolders.id.toString(),
    var title: String? = "Titel Saknas",
    var dealtext1: String = "Förmånstitel",
    var dealtext2: String = "Förmånsundertitel",
    var imageURL: String? = null,
    var content: String? = "Text Saknas",
    var link: String? = "Länk Saknas",
    var memberLink: String? = null,
    var category: MutableList<String>? = mutableListOf(),
) {
    companion object {

        val placeholder = Benefit(
            id = "1",
            title = "Förmånstitel",
            dealtext1 = "Kampanjerbjudande",
            dealtext2 = "Mer information",
            category = mutableListOf("Aktuellt", "Administration")
        )

        val placeholderList = listOf<Benefit>(
            Benefit(
                id = "1",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                category = mutableListOf("Aktuellt", "Administration")
            ),
            Benefit(
                id = "2",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                category = mutableListOf("Aktuellt", "Fordon")
            ),
            Benefit(
                id = "3",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                category = mutableListOf("Elektronik", "Administration")
            ),
            Benefit(
                id = "4",
                title = "Förmånstitel",
                dealtext1 = "Kampanjerbjudande",
                dealtext2 = "Mer information",
                category = mutableListOf("Aktuellt", "Administration")
            ),
        )
    }
}



