package com.levento.sfrapp.models

data class UserCompany(
    val memberId: String,
    val companyName: String,
    val orgNr: String,
    val locality: String,
    val address: String,
    val phone: String,
    val contact: String,
    val validThru: String
)