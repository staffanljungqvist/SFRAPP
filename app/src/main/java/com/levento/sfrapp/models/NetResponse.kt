package com.levento.sfrapp.models

data class NetResponse<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)