package com.example.challenge_mobile_recomendaes.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.adapters.CourseAdapter
import com.example.challenge_mobile_recomendaes.dao.CourseDao
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.models.Courses
import com.example.challenge_mobile_recomendaes.service.CourseService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var courseDao: CourseDao
    private lateinit var courseService: CourseService
    private lateinit var courseRecyclerView: RecyclerView
    private lateinit var courseAdapter: CourseAdapter
    private var courses: List<Courses> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val database = AppDatabase.getDatabase(this)
        courseDao = database.courseDao()
        courseService = CourseService(courseDao) // Ensure your CourseService takes CourseDao

        courseRecyclerView = findViewById(R.id.recycler_view_courses)
        courseRecyclerView.layoutManager = LinearLayoutManager(this)

        // Load courses from database
        loadCourses()
    }

    private fun loadCourses() {
        CoroutineScope(Dispatchers.IO).launch {
            courses = courseService.getAllCourses() // Fetching courses from database
            withContext(Dispatchers.Main) {
                courseAdapter = CourseAdapter(courses, this@HomeActivity)
                courseRecyclerView.adapter = courseAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_course -> {
                val intent = Intent(this, AddCourseActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
