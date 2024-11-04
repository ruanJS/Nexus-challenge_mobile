package com.example.challenge_mobile_recomendaes.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.dao.CourseDao
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.models.Courses
import com.example.challenge_mobile_recomendaes.service.CourseService

class AddCourseActivity : AppCompatActivity() {

    private lateinit var courseDao: CourseDao
    private lateinit var courseService: CourseService
    private lateinit var editTextCourseName: EditText
    private lateinit var buttonAddCourse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val database = AppDatabase.getDatabase(this)
        courseDao = database.courseDao()

        editTextCourseName = findViewById(R.id.editTextCourseName)
        buttonAddCourse = findViewById(R.id.buttonAddCourse)

        buttonAddCourse.setOnClickListener {
            val courseName = editTextCourseName.text.toString()
            if (courseName.isNotEmpty()) {
                val courses = Courses(name = courseName)
                courseService.addCourse(courses)
                Toast.makeText(this, "Course added!", Toast.LENGTH_SHORT).show()
                finish() // Close activity and return to HomeActivity
            } else {
                Toast.makeText(this, "Please enter a course name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Courses(name: String): Courses {
        return Courses(name)
    }
}
