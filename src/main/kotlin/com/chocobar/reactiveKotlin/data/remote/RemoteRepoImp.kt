package com.chocobar.reactiveKotlin.data.remote

import com.chocobar.reactiveKotlin.data.models.MovieRequest

class RemoteRepoImp(val remoteDataSource: RemoteDataSourceInt):IRemoteRepo {
    override suspend fun getMovieList(): MovieRequest {
        return remoteDataSource.getPopularMovies()
    }
}