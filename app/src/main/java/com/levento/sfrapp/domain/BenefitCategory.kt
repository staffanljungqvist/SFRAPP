package com.levento.sfrapp.domain

data class BenefitCategory(
    var imageURL: String? = null,
    var title: String? = "no title",
    var benefits: MutableList<Benefit> = mutableListOf()
)






data class Testobjekt (
    var enText: String? = "ingen text",
    var stringLista: MutableList<String>? = mutableListOf()
)

