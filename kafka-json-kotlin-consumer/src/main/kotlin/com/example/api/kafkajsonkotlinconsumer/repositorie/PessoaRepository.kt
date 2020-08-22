package com.example.api.kafkajsonkotlinconsumer.repositorie

import com.example.api.kafkajsonkotlinconsumer.entity.PessoaEntity
import entity.PessoaEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<PessoaEntity, Long>