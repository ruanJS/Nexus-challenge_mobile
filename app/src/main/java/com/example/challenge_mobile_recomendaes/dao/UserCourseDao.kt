package com.example.challenge_mobile_recomendaes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.challenge_mobile_recomendaes.models.UserCourses

@Dao
@Entity(tableName = "User_courses")
interface UserCourseDao {
    @Query("SELECT * FROM user_courses WHERE userId = :userId")
    fun getCoursesByUser(userId: Int): List<UserCourses>

    @Insert
    fun insertUserCourse(userCourse: UserCourses)

    @Delete
    fun deleteUserCourse(userCourse: UserCourses)
}
