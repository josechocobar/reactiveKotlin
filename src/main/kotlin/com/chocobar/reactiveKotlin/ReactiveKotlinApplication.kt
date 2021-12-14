package com.chocobar.reactiveKotlin

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Quest Api", version = "1.0",
description = "Documentation of db")
)
class ReactiveKotlinApplication

fun main(args: Array<String>) {
    runApplication<ReactiveKotlinApplication>(*args)
}



