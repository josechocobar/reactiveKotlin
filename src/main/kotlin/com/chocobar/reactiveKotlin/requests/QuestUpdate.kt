package com.chocobar.reactiveKotlin.requests

data class QuestUpdate(
    var id: Int?,
    var text: String,
    var answer1: String,
    var answer2: String,
    var answer3: String,
    var answer4: String,
    var correctAnswer: String
)
