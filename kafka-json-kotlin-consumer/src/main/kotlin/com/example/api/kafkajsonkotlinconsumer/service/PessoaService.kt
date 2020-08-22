package com.example.api.kafkajsonkotlinconsumer.service

import com.example.api.kafkajsonkotlinconsumer.entity.PessoaEntity


interface PessoaService {

    fun insert(pessoa : PessoaEntity)
}
