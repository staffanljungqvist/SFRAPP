package com.levento.sfrapp.domain

import com.levento.sfrapp.R

object PlaceHolders {
    val categoryImage = R.drawable.fordon_kategori
    val newsArticleImage = R.drawable.mala1
    val newsList = listOf<Article>(
        Article(
            id = "1",
            title = "Det här är en placeholder nyhetstext",
        ),
        Article(
            id ="2",
            title = "Det här är en placeholder nyhetstext",
        ),
        Article(
            "3",
            "Det här är en placeholder nyhetstext",
        )
    )
    val benefits = listOf<Benefit>(
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

    val categories = listOf(
        BenefitCategory(title = "Aktuellt"),
        BenefitCategory(title = "Administration"),
        BenefitCategory(title = "Fordon"),
        BenefitCategory(title = "Elektronik")
    )

    var id = 1
    get() {
        field++
        return field
    }
}