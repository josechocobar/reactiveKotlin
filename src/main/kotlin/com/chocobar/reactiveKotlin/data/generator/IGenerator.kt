package com.chocobar.reactiveKotlin.data.generator

import com.chocobar.reactiveKotlin.data.models.Quest
import reactor.core.publisher.Flux

interface IGenerator {
    suspend fun guessOriginalLanguage(): List<Quest>
    suspend fun guessOverview() : List<Quest>
}