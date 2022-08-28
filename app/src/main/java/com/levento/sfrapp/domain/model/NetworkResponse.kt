package com.levento.sfrapp.domain.model

data class NetworkResponse<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)