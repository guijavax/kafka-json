package com.example.api.kafka.impl

import com.example.api.config.KafkaConfig
import com.example.api.kafka.SendToKafka
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class SendToKafkaImpl : SendToKafka {
    @Autowired
    lateinit var kafkaConfig : KafkaConfig

    @Autowired
   private lateinit var kafkaTemplate : KafkaTemplate<String, String>

    private fun producerJson() =  kafkaConfig.producerJson()

    override fun sendToKafkaJson(topic : String, obj : Any) {
        val node = ObjectMapper().valueToTree<JsonNode>(obj)
        val record : ProducerRecord<String, JsonNode> = ProducerRecord<String, JsonNode>(topic, node)

        producerJson().send(record) { metadata: RecordMetadata?, exception: Exception? ->
            println(exception ?: metadata)
        }
    }

    override fun sendToKafkaString(topic: String, obj: Any) {
        kafkaTemplate.send(topic, obj as String)
    }

}