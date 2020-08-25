package com.example.api.kafka


interface SendToKafka {

    fun sendToKafkaJson(topic : String, obj : Any)

    fun sendToKafkaString(topic : String, obj: Any)
}