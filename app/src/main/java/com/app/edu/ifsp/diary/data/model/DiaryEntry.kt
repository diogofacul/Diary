package com.app.edu.ifsp.diary.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_entries")
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val annotation: String,
    val location: String,
    val date: String,
    val time: String
)
