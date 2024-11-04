package com.example.challenge_mobile_recomendaes.database

import com.example.challenge_mobile_recomendaes.database.AppDatabase
import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    @JvmStatic
    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "challenge_mobile_recommendations_db"
                ).build()
            }
        }
        return INSTANCE!!
    }
}
