package com.example.challenge_mobile_recomendaes.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R

class TestConnectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_connection)

        val connection = DatabaseConnection.getConnection()
        if (connection != null) {
            println("Conexão bem-sucedida!")
            connection.close()
        } else {
            println("Falha na conexão.")
        }
    }
}