package com.example.challenge_mobile_recomendaes.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.challenge_mobile_recomendaes.models.Recommendation

@Dao
@Entity(tableName = "Recommendations")
interface RecommendationDao {
    @Insert
    fun insertRecommendation(recommendation: Recommendation)

    @Query("SELECT * FROM recommendations WHERE userId = :userId")
    fun getRecommendationsForUser(userId: Long): List<Recommendation>
}
