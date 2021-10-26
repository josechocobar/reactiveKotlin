package com.chocobar.reactiveKotlin.data.remote

import com.chocobar.reactiveKotlin.data.models.MovieRequest

interface RemoteDataSourceInt {
    suspend fun getPopularMovies() : MovieRequest
}