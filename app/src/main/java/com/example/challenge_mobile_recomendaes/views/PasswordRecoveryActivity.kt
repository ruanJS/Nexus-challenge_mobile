package com.example.challenge_mobile_recomendaes.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.challenge_mobile_recomendaes.database.AppDatabase
import com.example.challenge_mobile_recomendaes.dao.UserDao
import kotlinx.coroutines.launch
import com.example.challenge_mobile_recomendaes.R

class PasswordRecoveryActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao
    private lateinit var usernameEditText: EditText
    private lateinit var recoverButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_recovery)

        val database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        usernameEditText = findViewById(R.id.usernameField)
        recoverButton = findViewById(R.id.recoverButton)

        recoverButton.setOnClickListener {
            recoverPassword()
        }
    }

    private fun recoverPassword() {
        val username = usernameEditText.text.toString()

        lifecycleScope.launch {
            val user = userDao.getAllUsers().find { it.username == username }
            if (user != null) {
                // Aqui você pode implementar um método de recuperação de senha, como enviar um email
                Toast.makeText(this@PasswordRecoveryActivity, "Password recovery instructions sent!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@PasswordRecoveryActivity, "User not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
