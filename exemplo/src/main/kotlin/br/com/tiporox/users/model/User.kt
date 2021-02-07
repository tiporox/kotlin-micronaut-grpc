package br.com.tiporox.users.model

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank


@Introspected
open class User {

    var id: String? = null

    @NotBlank
    var name: String? = null

    @NotBlank
    var idade: Int? = null

    @NotBlank(message =  "O campo sexo e obrigatorio")
    var sexo: String? = null


    constructor(
        id: String?,
        name: String,
        idade: Int,
        sexo: String
    ) {
        this.id = id
        this.name = name
        this.idade = idade
        this.sexo = sexo
    }

}
