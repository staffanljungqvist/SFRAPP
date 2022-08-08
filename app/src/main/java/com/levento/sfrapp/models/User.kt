package com.levento.sfrapp.models

data class User(
    val memberId: String? = null,
    val companyName: String? = null,
    val orgNr: String? = null,
    val locality: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val contactPerson: String? = null,
    val validThru: String? = null
)
