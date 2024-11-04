package com.example.challenge_mobile_recomendaes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Courses(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val courseName: String,
    val description: String,
    val categoryId: Int
) {
    val title: Any
        get() {
            TODO( "Not yet implemented")
        }
}
