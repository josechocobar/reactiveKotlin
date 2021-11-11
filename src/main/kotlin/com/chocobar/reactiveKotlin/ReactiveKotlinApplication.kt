package com.chocobar.reactiveKotlin

import com.chocobar.reactiveKotlin.data.generator.Generator
import com.chocobar.reactiveKotlin.data.models.Quest
import com.chocobar.reactiveKotlin.data.remote.*
import com.chocobar.reactiveKotlin.data.repository.QuestRepository
import com.chocobar.reactiveKotlin.data.repository.RepoImp
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveKotlinApplication
fun main(args: Array<String>) {
    runApplication<ReactiveKotlinApplication>(*args)
}



