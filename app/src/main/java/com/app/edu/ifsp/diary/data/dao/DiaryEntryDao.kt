package com.app.edu.ifsp.diary.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.edu.ifsp.diary.data.model.DiaryEntry

@Dao
interface DiaryEntryDao {
    @Query("SELECT * FROM diary_entries")
    fun getAllEntries(): LiveData<List<DiaryEntry>>

    @Insert
    suspend fun insert(entry: DiaryEntry)

    @Query("DELETE FROM diary_entries WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM diary_entries WHERE id = :id")
    suspend fun getEntryById(id: Int): DiaryEntry?
}
