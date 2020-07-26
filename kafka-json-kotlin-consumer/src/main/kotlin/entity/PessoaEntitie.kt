package entity

import com.sun.istack.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pessoa")
data class PessoaEntitie(
        @Id
        val id : Long = 0L,

        @Column
        @NotNull
        val name : String = "",

        @Column
        @NotNull
        val idade : Int = 0,

        @Column
        @NotNull
        val cpf : Long = 0
)