package com.levento.sfrapp.domain

data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)