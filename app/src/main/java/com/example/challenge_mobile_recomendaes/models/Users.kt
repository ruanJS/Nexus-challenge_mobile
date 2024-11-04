package com.example.challenge_mobile_recomendaes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val active: String = "true",  // Exemplo de valor padrão
    val date_of_birth: String = "2000-01-01", // Exemplo de valor padrão
    var email: String = "exemplo@email.com",
    val first_name: String = "Nome", // Valor padrão
    val last_name: String = "Sobrenome", // Valor padrão
    val password: String,
    var name: String = "Nome completo", // Valor padrão
    val username: String
)

