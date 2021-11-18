package com.chocobar.reactiveKotlin.data.generator

import com.chocobar.reactiveKotlin.data.models.Movie
import com.chocobar.reactiveKotlin.data.models.Quest
import com.chocobar.reactiveKotlin.data.repository.RepoImp

class Generator(var repoImp: RepoImp) : IGenerator {
    override suspend fun generateQuest(): List<Quest> {
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

        listOfMovies?.let {
            it.forEach { movie->
                listOfQuests.add(originalLanguage(movie,languageList))
                listOfQuests.add(overviewQuest(movie,listOfMovies))
            }
            return listOfQuests
        }
        throw Exception("Error en ")
    }

    private fun originalLanguage(
        movie: Movie,
        languageList:List<String>
    ): Quest {
            val originalLanguage = movie.original_language
            val languageMinus = languageList.shuffled()
            return (
                Quest(
                    null,
                    text = "¿Cuál es el lenguaje original de la pelicula ${movie.original_title}?",
                    answer1 = languageMinus[0],
                    answer2 = languageMinus[1],
                    answer3 = languageMinus[2],
                    answer4 = languageMinus[3],
                    correctAnswer = originalLanguage
                )
            )

    }
    private fun overviewQuest(movie: Movie,listOfMovie: List<Movie>):Quest{
        val originalOverView = movie.overview
        val listExceptuation = listOfMovie.minus(movie)
        val listShuffled = listExceptuation.shuffled()
        val listStrim = listShuffled.subList(0,3)
        val listOfTitles= mutableListOf<String>()
        listStrim.forEach {
            listOfTitles.add(it.original_title)
        }
        listOfTitles.add(movie.original_title)
        val listOfShuffledAgain = listOfTitles.shuffled()
        try {
            return Quest(
                id = null,
                text = originalOverView,
                answer1 = listOfShuffledAgain[0],
                answer2 = listOfShuffledAgain[1],
                answer3 = listOfShuffledAgain[2],
                answer4 = listOfShuffledAgain[3],
                correctAnswer = movie.original_title
            )
        }catch (e:Exception){
            println("Error en quest generations porque ${e.message}")
            throw Exception("error en quest gen", e)
        }
    }

}