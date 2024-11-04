package com.example.challenge_mobile_recomendaes.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.service.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var authService: AuthService
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupTextView: TextView
    private lateinit var passwordRecoveryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializando o DAO e AuthService
        val database = AppDatabase.getDatabase(this)
        val userDao = database.userDao()
        authService = AuthService(userDao)

        usernameEditText = findViewById(R.id.usernameField)
        passwordEditText = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)
        signupTextView = findViewById(R.id.signupText)
        passwordRecoveryTextView = findViewById(R.id.forgotPasswordText)

        loginButton.setOnClickListener {
            loginUser()
        }

        signupTextView.setOnClickListener {
            navigateToSignup()
        }

        passwordRecoveryTextView.setOnClickListener {
            navigateToPasswordRecovery()
        }
    }

    private fun loginUser() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val token = authService.login(username, password)
            runOnUiThread {
                if (token != null) {
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPasswordRecovery() {
        val intent = Intent(this, PasswordRecoveryActivity::class.java)
        startActivity(intent)
    }
}
