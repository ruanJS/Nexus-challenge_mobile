package com.example.challenge_mobile_recomendaes.controllers

import com.example.challenge_mobile_recomendaes.models.Users
import com.example.challenge_mobile_recomendaes.service.AuthService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

class  AuthController(private val authService: AuthService) {

    // Definir as rotas de autenticação
    fun Route.authRoutes() {
        route("/api/auth") {

            // Rota de login
            post("/login") {
                val users = call.receive<Users>()
                val token = authService.login(users.email, users.password)

                if (token != null) {
                    call.respond(HttpStatusCode.OK, mapOf("token" to token))
                } else {
                    call.respond(HttpStatusCode.Unauthorized, mapOf("error" to "Invalid credentials"))
                }
            }

            // Rota de registro
            post("/register") {
                val users = call.receive<Users>()
                val isRegistered = authService.register(users)

                if (isRegistered) {
                    call.respond(HttpStatusCode.Created, mapOf("message" to "User registered successfully"))
                } else {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Registration failed"))
                }
            }

            // Rota de recuperação de senha
            post("/recover") {
                val request = call.receive<Map<String, String>>()
                val email = request["email"]

                if (email != null) {
                    val isSent = authService.sendRecoveryEmail(email)

                    if (isSent) {
                        call.respond(HttpStatusCode.OK, mapOf("message" to "Recovery email sent"))
                    } else {
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Email not found"))
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid request format"))
                }
            }
        }
    }
}
