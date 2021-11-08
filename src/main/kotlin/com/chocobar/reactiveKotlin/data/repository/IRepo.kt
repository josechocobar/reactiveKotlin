package com.chocobar.reactiveKotlin.data.repository

import com.chocobar.reactiveKotlin.data.models.MovieRequest

interface IRepo {
    suspend fun getMovieList() : MovieRequest}