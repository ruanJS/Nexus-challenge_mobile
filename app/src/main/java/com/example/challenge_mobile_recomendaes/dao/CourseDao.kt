package com.example.challenge_mobile_recomendaes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.challenge_mobile_recomendaes.models.Courses

@Dao
@Entity(tableName = "courses")
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun getAllCourses(): List<Courses>

    @Insert
    fun insertCourse(course: Courses)

    @Update
    fun updateCourse(course: Courses)

    @Delete
    fun deleteCourse(course: Courses)
}
