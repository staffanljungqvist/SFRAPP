package com.levento.sfrapp.common.domain.model

data class NetworkResponse<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)