package com.example.challenge_mobile_recomendaes.test

fun main() {
    val connection = DatabaseConnection.getConnection()
    if (connection != null) {
        println("Conexão bem-sucedida!")
        connection.close()
    } else {
        println("Falha na conexão.")
    }
}
