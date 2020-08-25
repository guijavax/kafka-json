package com.example.api.dto

import javax.validation.constraints.*


data class Person(
        @field:NotBlank(message = "\${name.notblank}")
        val name : String,

        @field:NotBlank(message = "\${cpf.notblank}")
        var cpf : String,

        val idade : Int
)