package com.example.challenge_mobile_recomendaes.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.models.Users

class UserProfileActivity : AppCompatActivity() {

    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Simulação de dados do usuário logado
        users = Users(id = 1, name = "John Doe", email = "john@example.com", password = "123456")

        val nameField = findViewById<EditText>(R.id.nameField)
        val emailField = findViewById<EditText>(R.id.emailField)

        nameField.setText(users.name)
        emailField.setText(users.email)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            users.name = nameField.text.toString()
            users.email = emailField.text.toString()

            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Users(id: Int, name: String, email: String, password: String): Users {
        return Users(id, name, email, password)

    }
}
