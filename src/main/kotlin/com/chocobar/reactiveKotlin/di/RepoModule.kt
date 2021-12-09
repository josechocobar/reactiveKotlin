package com.chocobar.reactiveKotlin.di

import com.chocobar.reactiveKotlin.data.generator.Generator
import com.chocobar.reactiveKotlin.data.remote.RemoteDataSource
import com.chocobar.reactiveKotlin.data.repository.RepoImp
import org.koin.dsl.module


val repoModule = module {
    single { RemoteDataSource() }
    single { RepoImp(get()) }
    //factory { Generator(get()) }

}