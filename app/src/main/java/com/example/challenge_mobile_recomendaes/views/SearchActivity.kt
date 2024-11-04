package com.example.challenge_mobile_recomendaes.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.service.CourseService

class SearchActivity : AppCompatActivity() {

    private lateinit var courseService: CourseService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Obtenha a instância do AppDatabase e o CourseDao
        val courseDao = AppDatabase.getDatabase(this).courseDao()
        courseService = CourseService(courseDao)

        val searchField = findViewById<EditText>(R.id.searchField)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val searchResultsListView = findViewById<ListView>(R.id.searchResultsListView)

        searchButton.setOnClickListener {
            val query = searchField.text.toString()
            val searchResults = courseService.searchCourses(query)

            val courseTitles = searchResults.map { it.title }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseTitles)

            searchResultsListView.adapter = adapter
        }
    }
}
