package com.chocobar.reactiveKotlin.data.remote

import com.chocobar.reactiveKotlin.data.models.MovieRequest

class RemoteDataSource : RemoteDataSourceInt {
    override suspend fun getPopularMovies(): MovieRequest {
        return RetrofitService.webService.getPopularMovies()
    }
}