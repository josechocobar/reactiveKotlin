package com.chocobar.reactiveKotlin.requests

import javax.validation.constraints.NotEmpty

data class QuestCreateRequest(
    @field:NotEmpty
    var id : Int,

    @field:NotEmpty
    var text : String,

    @field:NotEmpty
    var answer1 :String,

    @field:NotEmpty
    var answer2 :String,

    @field:NotEmpty
    var answer3 :String,

    @field:NotEmpty
    var answer4 :String,

    @field:NotEmpty
    var correctAnswer :String,

)