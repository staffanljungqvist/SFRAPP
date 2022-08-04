package com.levento.sfrapp.models
import coil.request.ImageResult

data class BenefitCategory(
    var imageURL: String? = null,
    var image: ImageResult? = null,
    var title: String? = "no title",
    var benefits: MutableList<Benefit> = mutableListOf()
)

