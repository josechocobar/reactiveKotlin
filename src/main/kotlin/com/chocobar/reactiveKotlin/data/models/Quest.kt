package com.chocobar.reactiveKotlin.data.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("quests")
data class Quest(
    @Id
    var id:Int?=null,
    var text:String,
    var answer1:String,
    var answer2:String,
    var answer3:String,
    var answer4:String,
    var correctAnswer: String
)
