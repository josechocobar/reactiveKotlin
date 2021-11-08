package com.chocobar.reactiveKotlin.responses

data class PagingResponse<T>(
    val total : Long,
    val items : List<T>
)
