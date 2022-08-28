package com.levento.sfrapp.domain.model
import coil.request.ImageResult

data class BenefitCategory(
    var imageURL: String? = null,
    var image: ImageResult? = null,
    var title: String? = "no title",
    var benefits: MutableList<Benefit> = mutableListOf()
) {
    companion object {
        val placeholder = listOf(
            BenefitCategory(title = "Administration"),
            BenefitCategory(title = "Fordon"),
            BenefitCategory(title = "Aktuellt"),
            BenefitCategory(title = "Elektronik")
        )
    }
}

