package com.example.challenge_mobile_recomendaes.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.service.CourseService

class RecommendationsActivity : AppCompatActivity() {

    private lateinit var courseService: CourseService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendations)

        // Obtenha a instância do AppDatabase e o CourseDao
        val courseDao = AppDatabase.getDatabase(this).courseDao()
        courseService = CourseService(courseDao)

        // Configure a lista de recomendações
        val recommendationsListView = findViewById<ListView>(R.id.recommendationsListView)
        val recommendations = courseService.getRecommendations()

        val courseTitles = recommendations.map { it.title }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseTitles)

        recommendationsListView.adapter = adapter
    }
}
