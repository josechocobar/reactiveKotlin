package com.chocobar.reactiveKotlin

import com.chocobar.reactiveKotlin.data.models.Movie
import com.chocobar.reactiveKotlin.data.models.MovieRequest
import com.chocobar.reactiveKotlin.data.remote.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveKotlinApplication
fun main(args: Array<String>) {
	runApplication<ReactiveKotlinApplication>(*args)
	val remoteDataSource = RemoteDataSource()
	val remoteRepo = RemoteRepoImp(remoteDataSource = RemoteDataSource())
	val fakeRepo = MovieRequest(results = listOf(
		Movie(
		true,"Hola", listOf(1,2,3),12,"ING","Chavo","un niño",1.2,"www.postre.com","20/10/2005","elchavo",true,5.6,5
	)
	))
	val myFlow = flow {
		//emit(Resource.Loading)
		//try {
			emit(remoteRepo.remoteDataSource.getPopularMovies())
		//} catch (e: Exception) {
		//	emit(Resource.Failure(e))
		//}
	}
	runBlocking {
		myFlow
			.onCompletion {
				println("completado")
			}
			.catch {  }
			.collect(){
				it->
				println("${it.results}")
			}
	}


}



