package com.levento.sfrapp.feature_benefits.domain.model
import coil.request.ImageResult
import com.levento.sfrapp.feature_benefits.domain.model.Benefit

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

