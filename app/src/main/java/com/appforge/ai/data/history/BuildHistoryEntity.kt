package com.appforge.ai.data.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "build_history")
data class BuildHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val appName: String,
    val packageName: String,
    val timestamp: Long,
    val downloadUrl: String,
    val status: String // e.g., "COMPLETED", "FAILED", "PENDING"
)
