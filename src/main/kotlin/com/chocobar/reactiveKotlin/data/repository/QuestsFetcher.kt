package com.chocobar.reactiveKotlin.data.repository

import com.chocobar.reactiveKotlin.data.models.MovieRequest
import com.chocobar.reactiveKotlin.data.models.Quest
import kotlinx.coroutines.flow.Flow


interface QuestsFetcherInterface {
    fun fetchQuest(): Flow<MovieRequest>
    fun getRemoteRepository(): RepoImp
}