package com.example.api.config

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.KafkaProducer

interface KafkaConfig {

    fun producerJson() : KafkaProducer<String, JsonNode>

}