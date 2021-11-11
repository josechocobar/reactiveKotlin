package com.chocobar.reactiveKotlin.data.repository

import com.chocobar.reactiveKotlin.data.models.Quest
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@EnableR2dbcRepositories
interface QuestRepository : R2dbcRepository<Quest, Int> {
    fun findByid(id: Int): Mono<Quest>

    @Query("SELECT * FROM questS limit :limit offset :offset")
    fun findAllQuest(limit: Int, offset: Int): Flux<Quest>

    fun findByText(text:String) : Mono<Quest>




}