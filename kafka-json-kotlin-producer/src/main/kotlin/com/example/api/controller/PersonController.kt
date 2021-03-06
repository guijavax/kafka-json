package com.example.api.controller

import com.example.api.dto.Person
import com.example.api.kafka.SendToKafka
import com.example.api.utils.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/person")
class PersonController {


    @Value("\${topic-person}")
    lateinit var topic : String

    @Value("\${topic-person-string}")
    lateinit var topicString : String


    @Autowired
    private lateinit var sendToKafka : SendToKafka

    @PostMapping("/post/{type}")
    fun post(@Valid @RequestBody person : Person, @PathVariable(name="type") type : String) : ResponseEntity<Any> {
        val sizeCpf = person.cpf.toString().length
        if (validaCpf(sizeCpf)) return ResponseEntity.badRequest().body("Tamanho de cpf invalido")
        person.cpf = Util.removeSpecialCaracterFromString(person.cpf)
        return try {
            if (type == "json") {
                sendToKafka.sendToKafkaJson(topic, person)
            } else {
                sendToKafka.sendToKafkaString(topicString, person.toString())
            }
            ResponseEntity.ok().build()
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema ao enviar mensagem")
        }
    }

    private fun validaCpf(sizeCpf: Int): Boolean = sizeCpf != 13
}
