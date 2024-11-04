package com.example.challenge_mobile_recomendaes.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.challenge_mobile_recomendaes.models.Users

@Dao
@Entity(tableName = "users")
interface UserDao {

    @Insert
    suspend fun insertUser(user: Users)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<Users>

    // Método para login baseado no username e password
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun loginUser(username: String, password: String): Users?

    // Método para verificar se o usuário já existe pelo username
    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): Users?
}
