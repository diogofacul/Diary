package com.app.edu.ifsp.diary.data.repository

import androidx.lifecycle.LiveData
import com.app.edu.ifsp.diary.data.dao.DiaryEntryDao
import com.app.edu.ifsp.diary.data.model.DiaryEntry

class DiaryEntryRepository(private val diaryEntryDao: DiaryEntryDao) {

    fun getAllEntries(): LiveData<List<DiaryEntry>> = diaryEntryDao.getAllEntries()

    suspend fun insert(entry: DiaryEntry) = diaryEntryDao.insert(entry)

    suspend fun deleteById(id: Int) = diaryEntryDao.deleteById(id)

    suspend fun getEntryById(id: Int): DiaryEntry? = diaryEntryDao.getEntryById(id)
}
