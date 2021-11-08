package com.chocobar.reactiveKotlin.data.repository

import com.chocobar.reactiveKotlin.data.models.MovieRequest
import com.chocobar.reactiveKotlin.data.remote.RemoteDataSourceInt

class RepoImp(val remoteDataSource: RemoteDataSourceInt): IRepo {
    override suspend fun getMovieList(): MovieRequest {
        return remoteDataSource.getPopularMovies()
    }
}