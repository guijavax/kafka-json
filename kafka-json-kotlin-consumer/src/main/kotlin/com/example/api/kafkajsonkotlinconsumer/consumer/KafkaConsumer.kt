package com.example.api.kafkajsonkotlinconsumer.consumer

import com.example.api.kafkajsonkotlinconsumer.dto.Pessoa
import com.google.gson.Gson
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    @KafkaListener(topics = ["pessoa"], groupId = "json")
    fun messageConsumer(@Payload data : ConsumerRecord<String, Pessoa>, @Headers headers: MessageHeaders) {
        val record = data.value() as String
        val pessoa : Pessoa = Gson().fromJson(record, Pessoa::class.java)
        LOGGER.info(pessoa.toString())
    }
}