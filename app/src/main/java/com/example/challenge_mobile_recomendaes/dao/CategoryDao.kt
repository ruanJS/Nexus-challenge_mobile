package com.example.challenge_mobile_recomendaes.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.challenge_mobile_recomendaes.models.Category

@Dao
@Entity(tableName = "category")
interface CategoryDao {
    @Insert
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Long): Category?

    @Query("SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>
}
