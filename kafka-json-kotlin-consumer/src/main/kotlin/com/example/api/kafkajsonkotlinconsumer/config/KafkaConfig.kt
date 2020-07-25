package com.example.api.kafkajsonkotlinconsumer.config

import com.example.api.kafkajsonkotlinconsumer.pessoa.Pessoa
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConfig {

    @Value("\${bootstrap.servers}")
    private lateinit var servers : String

    private fun setProperties() : Map<String, Any> {
        return HashMap<String, Any>().apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer::class.java)
            put(ConsumerConfig.GROUP_ID_CONFIG, "json")
        }
    }

   private fun consumerFactory() : ConsumerFactory<String, Pessoa> {
       return DefaultKafkaConsumerFactory(setProperties(), StringDeserializer(), JsonDeserializer(Pessoa::class.java))
   }

    @Bean
    fun kafkaListenerFactory() : ConcurrentKafkaListenerContainerFactory<String, Pessoa> {
          return  ConcurrentKafkaListenerContainerFactory<String, Pessoa>().apply {
              consumerFactory = consumerFactory()
          }
    }
}