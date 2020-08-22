package com.example.api.kafkajsonkotlinconsumer.service.impl

import com.example.api.kafkajsonkotlinconsumer.dto.Pessoa
import com.example.api.kafkajsonkotlinconsumer.entity.PessoaEntity
import com.example.api.kafkajsonkotlinconsumer.repositorie.PessoaRepository
import com.example.api.kafkajsonkotlinconsumer.service.PessoaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PessoaServiceImpl : PessoaService {

    private val LOGGER : Logger = LoggerFactory.getLogger(PessoaServiceImpl::class.java)

    @Autowired
    private lateinit var pessoaRepository : PessoaRepository
    override fun insert(pessoa: PessoaEntity) {
        try {
            pessoaRepository.save(pessoa)
        } catch (e : Exception) {
            LOGGER.error(e.message)
        }
    }

}