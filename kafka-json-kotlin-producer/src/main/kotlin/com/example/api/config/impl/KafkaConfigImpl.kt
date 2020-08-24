package com.example.api.config.impl

import com.example.api.config.KafkaConfig
import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfigImpl : KafkaConfig {

    @Value("\${bootstrap.servers}")
    private lateinit var servers : String

    private fun config(typeSerializer : String) : Map<String, Any> {
        return HashMap<String, Any>().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            when (typeSerializer) {
                "json" -> put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
                else -> {
                    put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
                }
            }
         }
    }

    @Bean
    override fun producerJson() : KafkaProducer<String, JsonNode> = KafkaProducer(config("json"))

    @Bean
    override fun producerString(): KafkaProducer<String, String> = KafkaProducer(config("string"))
}