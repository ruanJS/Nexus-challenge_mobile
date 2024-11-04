package com.example.challenge_mobile_recomendaes.controllers

import com.example.challenge_mobile_recomendaes.models.Courses
import com.example.challenge_mobile_recomendaes.service.CourseService
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*

class CourseController(private val courseService: CourseService) {

    fun Route.courseRoutes() {
        route("/api/courses") {

            // Endpoint para recomendações
            get("/recommendations") {
                val recommendations = courseService.getRecommendations()
                call.respond(HttpStatusCode.OK, recommendations)
            }

            // Endpoint para busca de cursos
            get("/search") {
                val query = call.request.queryParameters["query"]
                val courses = courseService.searchCourses(query)
                call.respond(HttpStatusCode.OK, courses)
            }

            // Endpoint para criação de um novo curso
            post("/") {
                val course = call.receive<Courses>()
                val createdCourse = courseService.addCourse(course)
                call.respond(HttpStatusCode.Created, createdCourse)
            }

            // Endpoint para atualização de curso
            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                val course = call.receive<Courses>()

                if (id != null && courseService.updateCourse(id, course)) {
                    call.respond(HttpStatusCode.OK, "Course updated successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Course not found")
                }
            }

            // Endpoint para exclusão de curso
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id != null && courseService.deleteCourse(id)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Course not found")
                }
            }
        }
    }
}
