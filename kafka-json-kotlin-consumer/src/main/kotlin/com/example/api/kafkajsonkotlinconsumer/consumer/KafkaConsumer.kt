package com.example.api.kafkajsonkotlinconsumer.consumer

import com.example.api.kafkajsonkotlinconsumer.dto.Pessoa
import com.example.api.kafkajsonkotlinconsumer.entity.PessoaEntity
import com.example.api.kafkajsonkotlinconsumer.service.PessoaService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {

    companion object {
        private val LOGGER : Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)
    }

    @Autowired
    private lateinit var service : PessoaService

    @KafkaListener(topics = ["pessoa"], groupId = "json")
    fun messageConsumerJson(@Payload data : ConsumerRecord<String, Pessoa>, @Headers headers: MessageHeaders) {
        val record = data.value() as String
        val pessoa : Pessoa = Gson().fromJson(record, Pessoa::class.java)

        service.insert(PessoaEntity(null, pessoa.name, pessoa.cpf, pessoa.idade))

        LOGGER.info(pessoa.toString())
    }

    @KafkaListener(topics = ["pessoa_string"], groupId = "string")
    fun message(message: String) {
        val gson = Gson()
        val pessoaJson = JsonParser().parse(message)
        val  pessoa = gson.fromJson(pessoaJson, Pessoa::class.java)
        LOGGER.info(pessoa.name)
    }

}