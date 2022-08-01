package com.levento.sfrapp.models

data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)