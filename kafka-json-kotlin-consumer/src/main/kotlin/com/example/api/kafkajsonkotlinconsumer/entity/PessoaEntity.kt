package com.example.api.kafkajsonkotlinconsumer.entity

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "pessoa")
data class PessoaEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pessoa")
        @SequenceGenerator(name = "id_pessoa", sequenceName = "seq_id_pessoa", initialValue = 1, allocationSize = 1)
        val idPessoa : Long? = 0,

        @Column(name= "nome")
        @NotNull
        val nome : String,

        @Column(name = "cpf")
        @NotNull
        val cpf : Long,

        @Column(name = "idade")
        @NotNull
        val idade : Int

) {
}