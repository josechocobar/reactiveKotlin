package com.chocobar.reactiveKotlin.data.generator

import com.chocobar.reactiveKotlin.data.models.Quest
import com.chocobar.reactiveKotlin.data.repository.RepoImp
import kotlinx.coroutines.*
import reactor.core.publisher.Flux

class Generator(var repoImp: RepoImp) : IGenerator {
    override suspend fun guessOriginalLanguage(): List<Quest> {
        val listOfQuests = mutableListOf<Quest>()

        /*
        aqui lo que tengo que hacer es generar la lista de las preguntas a partir de los datos en repo
        luego de generar lo que tengo que hacer es guardarla en h2 de manera que se pueda leer a traves de una api rest
        entonces lo primero a hacer es generar la lista
         */

        val listOfMovies = repoImp.getMovieList().results
        val languageList = listOf<String>("en", "fr", "jp", "es")

        /*
        para generar la lista de las preguntas, lo que voy a hacer es definir las variables a tener en cuenta

         */

        listOfMovies?.size?.let {
            for (i in 0 until it-1) {
                val originalLanguage = listOfMovies[i].original_language
                val languageMinus = languageList.shuffled()
                listOfQuests.add(
                    Quest(
                        i + 1,
                        text = "¿Cuál es el lenguaje original de la pelicula ${listOfMovies[i].original_title}?",
                        answer1 = languageMinus[0],
                        answer2 = languageMinus[1],
                        answer3 = languageMinus[2],
                        answer4 = languageMinus[3],
                        correctAnswer = originalLanguage
                    )
                )
            }


        }
        return listOfQuests
    }


}