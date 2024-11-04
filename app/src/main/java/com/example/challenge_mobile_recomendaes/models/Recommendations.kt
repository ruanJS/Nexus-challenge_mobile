package com.example.challenge_mobile_recomendaes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommendations")
data class Recommendation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long, // Referência ao ID do usuário
    val courseId: Long, // Referência ao ID do curso
    val recommendationText: String
)
