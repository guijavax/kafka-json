package com.example.api.dto

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty


data class Person(
        @field:NotBlank(message = "\${name.notblank}")
        val name : String,
        val cpf : Long,
        val idade : Int
)