package com.example.challenge_mobile_recomendaes.service

import android.util.Log
import com.example.challenge_mobile_recomendaes.models.Users
import com.example.challenge_mobile_recomendaes.dao.UserDao

class AuthService(private val userDao: UserDao) { // Injetando o UserDao via construtor

    // Método de login que valida as credenciais do usuário pelo username
    fun login(username: String, password: String): String? {
        Log.d("AuthService", "Attempting login for username: $username") // Log do username usado na consulta

        val user: Users? = userDao.loginUser(username, password)
        if (user != null) {
            Log.d("AuthService", "Login successful for user: ${user.username}") // Log para confirmar que o usuário foi encontrado
            return "dummyToken" // Troque para o token correto, se houver
        } else {
            Log.d("AuthService", "Login failed: User not found or invalid credentials") // Log se as credenciais forem inválidas
            return null
        }
    }

    // Método de registro de um novo usuário
    suspend fun register(user: Users): Boolean {
        if (userDao.getUserByUsername(user.username) != null) { // Alteração para validar pelo username
            return false // Usuário já existe
        }
        userDao.insertUser(user)
        return true
    }

    // Envio de email de recuperação de senha (simulação)
    fun sendRecoveryEmail(username: String?): Boolean {
        val user = userDao.getUserByUsername(username ?: "")
        return if (user != null) {
            // Simula o envio de um email (substitua por uma lógica real de envio de email)
            Log.d("AuthService", "Enviando email de recuperação para: ${user.email}")
            true
        } else {
            Log.d("AuthService", "Recuperação de senha falhou: usuário não encontrado para o username: $username")
            false
        }
    }
}
