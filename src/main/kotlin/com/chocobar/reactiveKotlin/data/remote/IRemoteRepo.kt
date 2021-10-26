package com.chocobar.reactiveKotlin.data.remote

import com.chocobar.reactiveKotlin.data.models.MovieRequest

interface IRemoteRepo {
    suspend fun getMovieList() : MovieRequest}