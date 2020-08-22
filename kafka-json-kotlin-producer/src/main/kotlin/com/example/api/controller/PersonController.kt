package com.example.api.controller

import com.example.api.config.KafkaConfig
import com.example.api.dto.Person
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    lateinit var kafkaConfig : KafkaConfig

    @Value("\${topic-person}")
    lateinit var topic : String

    @PostMapping("/post")
    fun post(@Valid @RequestBody person : Person) : ResponseEntity<Any> {
        val producer = kafkaConfig.producer()

        val node = ObjectMapper().valueToTree<JsonNode>(person)
        val record : ProducerRecord<String, JsonNode> = ProducerRecord<String, JsonNode>(topic, node)

        return try {
            producer.send(record) { metadata: RecordMetadata?, exception: Exception? ->
                println(exception?:metadata)
            }
            ResponseEntity.ok().build()
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema ao enviar mensagem")
        }
    }
}