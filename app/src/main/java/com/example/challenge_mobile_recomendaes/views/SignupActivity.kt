package com.example.challenge_mobile_recomendaes.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.dao.UserDao
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.models.Users
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        usernameEditText = findViewById(R.id.usernameField)
        passwordEditText = findViewById(R.id.passwordField)
        signupButton = findViewById(R.id.signupButton)

        signupButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val newUser = Users(username = username, password = password)
        lifecycleScope.launch {
            userDao.insertUser(newUser)
            Toast.makeText(this@SignupActivity, "User registered successfully!", Toast.LENGTH_SHORT)
                .show()

            // Navegar para a tela de login
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
