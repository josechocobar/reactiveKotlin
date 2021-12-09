package com.chocobar.reactiveKotlin.data.repository

import com.chocobar.reactiveKotlin.data.models.MovieRequest
import com.chocobar.reactiveKotlin.data.models.Quest

interface IRepo {
    suspend fun getMovieList(): MovieRequest
}