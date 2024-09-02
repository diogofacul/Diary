package com.app.edu.ifsp.diary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.edu.ifsp.diary.data.model.DiaryEntry
import kotlinx.coroutines.launch
import com.app.edu.ifsp.diary.data.repository.DiaryEntryRepository
import com.app.edu.ifsp.diary.data.database.DiaryDatabase

class DiaryEntryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DiaryEntryRepository
    val allEntries: LiveData<List<DiaryEntry>>

    init {
        val diaryEntryDao = DiaryDatabase.getDatabase(application).diaryEntryDao()
        repository = DiaryEntryRepository(diaryEntryDao)
        allEntries = repository.getAllEntries()
    }

    fun insert(entry: DiaryEntry) = viewModelScope.launch {
        repository.insert(entry)
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        repository.deleteById(id)
    }

    suspend fun getEntryById(id: Int): DiaryEntry? = repository.getEntryById(id)
}
