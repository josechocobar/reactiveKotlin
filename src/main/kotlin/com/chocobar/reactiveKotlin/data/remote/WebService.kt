package com.chocobar.reactiveKotlin.data.remote

import com.chocobar.reactiveKotlin.data.models.MovieRequest
import retrofit2.http.GET

interface WebService {

    @GET("popular?api_key=0a8956c5ce74c43236f60c0fe039e3c1&language=en-US&page=1")
    suspend fun getPopularMovies(): MovieRequest

    //@GET("/3/movie/")
    //suspend fun getPopulars(@Query(value = "$API_KEY"))
}