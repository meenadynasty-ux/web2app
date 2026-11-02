package com.appforge.ai.data.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildHistoryDao {
    @Query("SELECT * FROM build_history ORDER BY timestamp DESC")
    fun getAllBuilds(): Flow<List<BuildHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuild(build: BuildHistoryEntity)
    
    @Query("DELETE FROM build_history WHERE id = :buildId")
    suspend fun deleteBuild(buildId: Int)
}
