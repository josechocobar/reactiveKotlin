package com.chocobar.reactiveKotlin

import com.chocobar.reactiveKotlin.data.generator.Generator
import com.chocobar.reactiveKotlin.data.remote.*
import com.chocobar.reactiveKotlin.data.repository.RepoImp
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveKotlinApplication

fun main(args: Array<String>) {
    runApplication<ReactiveKotlinApplication>(*args)

    val remoteRepo = RepoImp(remoteDataSource = RemoteDataSource())
    val myFlow = flow {
        emit(remoteRepo.remoteDataSource.getPopularMovies())
    }
    runBlocking {
        myFlow
            .onCompletion {
                println("completado")
            }
            .catch { }
            .collect() { elements ->
                run {
                    val generator = Generator(remoteRepo).guessOriginalLanguage()
                    print("${generator[1].text}\n${generator[1].answer1}\n${generator[1].answer2}\n${generator[1].answer3}\n${generator[1].answer4}\n")
                    val size = elements.results?.size
                    size?.let {
                        for (i in 1..size) {
                            println(elements.results?.get(i - 1))
                        }
                    }

                }
            }
    }



}



