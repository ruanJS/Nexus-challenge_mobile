package com.example.challenge_mobile_recomendaes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge_mobile_recomendaes.dao.CategoryDao
import com.example.challenge_mobile_recomendaes.dao.CourseDao
import com.example.challenge_mobile_recomendaes.dao.UserDao
import com.example.challenge_mobile_recomendaes.models.Category
import com.example.challenge_mobile_recomendaes.models.Courses
import com.example.challenge_mobile_recomendaes.models.Users

@Database(entities = [Users::class, Category::class, Courses::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

