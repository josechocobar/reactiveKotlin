package com.chocobar.reactiveKotlin.controller

import com.chocobar.reactiveKotlin.data.models.Quest
import com.chocobar.reactiveKotlin.data.repository.QuestRepository
import com.chocobar.reactiveKotlin.requests.QuestCreateRequest
import com.chocobar.reactiveKotlin.requests.QuestUpdateRequest
import com.chocobar.reactiveKotlin.responses.PagingResponse
import com.chocobar.reactiveKotlin.responses.QuestRequestResponse
import com.chocobar.reactiveKotlin.responses.QuestUpdateResponse
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/quests", produces = [MediaType.APPLICATION_JSON_VALUE])
class QuestController {

    @Autowired
    lateinit var questRepository: QuestRepository

    suspend fun createQuest(
        @RequestBody @Valid request: QuestCreateRequest
    ): QuestRequestResponse {
        var existingQuest = questRepository.findByid(request.id).awaitFirstOrNull()
        if(existingQuest !=null){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Quest")
        }
        val quest = Quest(
            id = null,
            text = request.text,
            answer1 = request.answer1,
            answer2 = request.answer2,
            answer3 = request.answer3,
            answer4 = request.answer4,
            correctAnswer = request.correctAnswer
        )
        val createdQuest = try {
            questRepository.save(quest).awaitFirstOrNull()
        }catch (e:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create quest",e)
        }
        val id = createdQuest?.id ?:
        throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Missing id for created quest")
        return mapResponse(id,createdQuest)

    }

    @GetMapping("")
    suspend fun listQuest(
        @RequestParam pageNo:Int = 1,
        @RequestParam pageSize:Int = 10
    ): PagingResponse<Quest> {
        var limit = pageSize
        var offset = (limit*pageNo)-limit
        var list = questRepository.findAllQuest(limit,offset).collectList().awaitFirst()
        val total = questRepository.count().awaitFirst()
        return PagingResponse(total, list)
    }
    @PatchMapping("/{questId}")
    suspend fun updateQuest(
        @PathVariable questId : Int,
        @RequestBody @Valid questUpdateRequest : QuestUpdateRequest
    ) : QuestUpdateResponse{
        var existingQuest = questRepository.findByid(questId).awaitFirstOrElse {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Quest #${questId} doesn't  exit")
        }
        val duplicateQuest = questUpdateRequest.id?.let { questRepository.findByid(it).awaitFirstOrNull() }
        if (duplicateQuest !=null && duplicateQuest.id !=questId){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Duplicate user: user with id ${questUpdateRequest.id} already exist"
            )
        }
        val updatedQuest = try{
        existingQuest.id = questUpdateRequest.id //Si la saco funca?
        existingQuest.text = questUpdateRequest.text
        existingQuest.answer1 = questUpdateRequest.answer1
        existingQuest.answer2 = questUpdateRequest.answer2
        existingQuest.answer3 = questUpdateRequest.answer3
        existingQuest.answer4 = questUpdateRequest.answer4
        existingQuest.correctAnswer = questUpdateRequest.correctAnswer
        questRepository.save(existingQuest).awaitFirst()}
        catch (e:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to update quest",e)
        }

        return QuestUpdateResponse(
            id = updatedQuest.id,
            text = updatedQuest.text,
            answer1 = updatedQuest.answer1,
            answer2 = updatedQuest.answer2,
            answer3 = updatedQuest.answer3,
            answer4 = updatedQuest.answer4,
            correctAnswer = updatedQuest.correctAnswer
        )



    }

    @DeleteMapping("/{questId}")
    suspend fun deleteQuest(
        @PathVariable questId: Int
    ){
        val existingQuest = questRepository.findByid(questId).awaitFirstOrElse {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Quest not found")
        }
        questRepository.delete(existingQuest).subscribe()
    }

    @GetMapping("/{questId}")
    suspend fun getQuest(
        @PathVariable questId: Int
    ):QuestRequestResponse{
        val quest = try {
            questRepository.findByid(questId).awaitFirst()
        }catch (e:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"No quest with id: $questId",e)
        }
        quest?.let {
            return mapResponse(questId,quest)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Error with id:$questId")


    }

    @GetMapping("/random")
    suspend fun getRandomQuest():QuestRequestResponse{
        val entityNumbers = questRepository.count().awaitFirst()
        val randomEntityId= (0 until entityNumbers-1).random().toInt()
        val quest = try {
            questRepository.findByid(randomEntityId).awaitFirst()
        }catch (e:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"No quest with id: $randomEntityId",e)
        }
        quest?.let {
            return mapResponse(randomEntityId,quest)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Error with id:$randomEntityId")


    }
    fun mapResponse(id:Int,quest: Quest):QuestRequestResponse{
        return QuestRequestResponse(
            id = id,
            text = quest.text,
            answer1 = quest.answer1,
            answer2 = quest.answer2,
            answer3 = quest.answer3,
            answer4 = quest.answer4
        )
    }
}