package com.example.challenge_mobile_recomendaes.views

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge_mobile_recomendaes.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val notificationSwitch = findViewById<Switch>(R.id.notificationSwitch)
        val darkModeSwitch = findViewById<Switch>(R.id.darkModeSwitch)

        // Simulação de carregamento das preferências salvas
        notificationSwitch.isChecked = loadNotificationPreference()
        darkModeSwitch.isChecked = loadDarkModePreference()

        // Ao mudar o estado do switch de notificações
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveNotificationPreference(isChecked)
            Toast.makeText(this, if (isChecked) "Notifications enabled" else "Notifications disabled", Toast.LENGTH_SHORT).show()
        }

        // Ao mudar o estado do switch de modo escuro
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveDarkModePreference(isChecked)
            Toast.makeText(this, if (isChecked) "Dark mode enabled" else "Dark mode disabled", Toast.LENGTH_SHORT).show()
        }
    }

    // Simulação de carregamento e salvamento das preferências (substitua por SharedPreferences ou outra solução real)
    private fun loadNotificationPreference(): Boolean {
        return false // Default: Notificações desligadas
    }

    private fun saveNotificationPreference(isEnabled: Boolean) {
        // Salvar a preferência de notificação (ex: em SharedPreferences)
    }

    private fun loadDarkModePreference(): Boolean {
        return false // Default: Modo escuro desligado
    }

    private fun saveDarkModePreference(isEnabled: Boolean) {
        // Salvar a preferência de modo escuro (ex: em SharedPreferences)
    }
}
