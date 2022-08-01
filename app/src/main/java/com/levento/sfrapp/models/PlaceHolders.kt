package com.levento.sfrapp.models

import com.levento.sfrapp.R

object PlaceHolders {
    val categoryImage = R.drawable.fordon_kategori
    val newsArticleImage = R.drawable.mala1

    val userCompany = UserCompany(
        memberId = "123456789",
        companyName = "LV Creative Consultants AB",
        orgNr = "12345",
        locality = "Helsingborg",
        address = "HETCH AB, Redaregatan 48",
        phone = "0708 1234567",
        contact = "Kalle Johansson",
        validThru = "2023-03-12"
    )


    val newsList = listOf<Article>(
        Article(
            id = "1",
            title = "Det här är en placeholder nyhetstext",
            content = "<![CDATA[\n" +
                    "<p>Småföretagarnas Riksförbund och <a href=\"https://bit.ly/Skåne_ReLoad\" target=\"_blank\" rel=\"noreferrer noopener\">Affärskompetens</a> anordnade för första gången en nätverksträff för de företagare som deltar i EU-projektet ReLoad Skåne. I ett sommarhett Törringelund presenteras Sonny Jerlström som driver <a href=\"https://www.jerlstromssnickeri.se/\" target=\"_blank\" rel=\"noreferrer noopener\">speciallösningar inom snickeri</a> och tillika <a href=\"https://smaforetagarna.se/forman/medlemskap-online/\" target=\"_blank\" rel=\"noreferrer noopener\">medlem i Småföretagarnas Riksförbund</a>. Han har under en tid varit i en utvecklingsfas för sitt företag och behövt hantera personal och personalfrågor. </p>"
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