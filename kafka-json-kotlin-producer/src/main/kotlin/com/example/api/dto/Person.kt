package com.example.api.dto

import javax.validation.constraints.*


data class Person(
        @field:NotBlank(message = "\${name.notblank}")
        val name : String,

        @field:NotNull(message = "\${cpf.notblank}")
        val cpf : Long,

        val idade : Int
)