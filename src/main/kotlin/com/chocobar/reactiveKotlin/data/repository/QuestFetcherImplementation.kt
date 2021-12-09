package com.chocobar.reactiveKotlin.data.repository

import com.chocobar.reactiveKotlin.data.models.MovieRequest
import com.chocobar.reactiveKotlin.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuestFetcherImplementation : QuestsFetcherInterface {

    val remoteRepo = RepoImp(remoteDataSource = RemoteDataSource())
    override fun fetchQuest(): Flow<MovieRequest> = flow {
        try {
            emit(remoteRepo.remoteDataSource.getPopularMovies())
            //throw IllegalStateException("Playing with exceptions.")
        } catch (e: Exception) {
            print("Exception Caught [ $e ]")
        }
    }

    override fun getRemoteRepository(): RepoImp {
        return remoteRepo
    }
}