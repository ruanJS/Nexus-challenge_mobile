package com.example.challenge_mobile_recomendaes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_courses")
data class UserCourses(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val courseId: Int
)
