package com.example.api.kafkajsonkotlinconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaJsonKotlinConsumerApplication

fun main(args: Array<String>) {
	runApplication<KafkaJsonKotlinConsumerApplication>(*args)
}
