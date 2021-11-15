package com.chocobar.reactiveKotlin.data.generator

import com.chocobar.reactiveKotlin.data.models.Movie
import com.chocobar.reactiveKotlin.data.models.Quest
import com.chocobar.reactiveKotlin.data.repository.RepoImp

class Generator(var repoImp: RepoImp) : IGenerator {
    override suspend fun guessOriginalLanguage(): List<Quest> {
        val listOfQuests = mutableListOf<Quest>()
        val listOfMovies = repoImp.getMovieList().results
        val languageList = listOf<String>("en", "fr", "jp", "es")
        listOfMovies?.let {
            listOfMovies.forEach {
                listOfQuests.add(mapQuestLanguage(it.id, listOfMovies, languageList))
            }
        }
        return listOfQuests
    }

    private fun mapQuestLanguage(
        id: Int,
        listOfMovies: List<Movie>,
        languageList: List<String>,
    ): Quest {
        val originalLanguage = listOfMovies[id].original_language
        val languageMinus = languageList.shuffled()
        return Quest(
            id,
            text = "¿Cuál es el lenguaje original de la pelicula ${listOfMovies[id].original_title}?",
            answer1 = languageMinus[0],
            answer2 = languageMinus[1],
            answer3 = languageMinus[2],
            answer4 = languageMinus[3],
            correctAnswer = originalLanguage
        )
    }

    override suspend fun guessOverview(): List<Quest> {
        val listOfQuests = mutableListOf<Quest>()
        val listOfMovies = repoImp.getMovieList().results
        listOfMovies?.let {
            val listofName = getName(listOfMovies)
            it.forEach { movie ->
                val randomNames = getRandomName(listofName)
                randomNames.add(movie.original_title)
                randomNames.shuffled()
                listOfQuests.add(
                    Quest(
                        movie.id,
                        text = "la siguiente reseña ${movie.overview} corresponde a... ",
                        randomNames[0],
                        randomNames[1],
                        randomNames[2],
                        randomNames[3],
                        correctAnswer = movie.original_title
                    )
                )
            }
        }
        return listOfQuests

    }

    private fun getOverviews(listOfMovies: List<Movie>): List<String> {
        val listOverview = mutableListOf<String>()
        listOfMovies.forEach {
            listOverview.add(it.overview)
        }
        return listOverview
    }

    private fun getName(listOfMovies: List<Movie>): List<String> {
        val listOfNames = mutableListOf<String>()
        listOfMovies.forEach {
            listOfNames.add(it.original_title)
        }
        return listOfNames
    }

    private fun getRandomName(listOfNames: List<String>): MutableList<String> {
        return listOfNames.shuffled().subList(0, 2) as MutableList<String>
    }
}