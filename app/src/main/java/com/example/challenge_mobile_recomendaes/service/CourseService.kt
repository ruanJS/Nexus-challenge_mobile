package com.example.challenge_mobile_recomendaes.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.challenge_mobile_recomendaes.dao.CourseDao
import com.example.challenge_mobile_recomendaes.models.Courses

class CourseService(courseDao: CourseDao) : CourseDao {

    private val courses = mutableListOf<Courses>() // Simulação de um banco de dados em memória

    // Método para adicionar um novo curso
    fun addCourse(course: Courses): Courses {
        course.id = (courses.size + 1)
        courses.add(course)
        return course
    }

    // Método para obter recomendações de cursos (baseado em alguma lógica)
    fun getRecommendations(): List<Courses> {
        // Aqui você pode adicionar lógica para retornar cursos personalizados
        return courses.take(5) // Simula o retorno dos 5 primeiros cursos
    }

    // Método para buscar cursos com base em uma palavra-chave
    fun searchCourses(query: String?): List<Courses> {
        return if (query.isNullOrBlank()) {
            courses
        } else {
            courses.filter { it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true) }
        }
    }

    // Método para atualizar um curso existente
    fun updateCourse(id: Int, updatedCourse: Courses): Boolean {
        val index = courses.indexOfFirst { it.id == id }
        return if (index != -1) {
            courses[index] = updatedCourse.copy(id = id)
            true
        } else {
            false
        }
    }

    // Método para excluir um curso
    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteCourse(id: Int): Boolean {
        return courses.removeIf { it.id == id }
    }

    override fun getAllCourses(): List<Courses> {
        TODO("Not yet implemented")
    }

    override fun insertCourse(course: Courses) {
        TODO("Not yet implemented")
    }

    override fun updateCourse(course: Courses) {
        TODO("Not yet implemented")
    }

    override fun deleteCourse(course: Courses) {
        TODO("Not yet implemented")
    }
}

private fun Any.contains(query: String, ignoreCase: Boolean): Boolean {
    return false
}
