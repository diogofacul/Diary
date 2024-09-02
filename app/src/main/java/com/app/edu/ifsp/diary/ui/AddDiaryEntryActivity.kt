package com.app.edu.ifsp.diary.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.edu.ifsp.diary.data.model.DiaryEntry
import com.app.edu.ifsp.diary.viewmodel.DiaryEntryViewModel
import com.app.edu.ifsp.diary.databinding.ActivityAddDiaryEntryBinding
import java.util.Calendar

class AddDiaryEntryActivity : AppCompatActivity() {

    private val viewModel: DiaryEntryViewModel by viewModels()
    private var date: String = ""
    private var time: String = ""
    private lateinit var binding : ActivityAddDiaryEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDiaryEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateButton.setOnClickListener {
            showDatePickerDialog()
        }

        binding.timeButton.setOnClickListener {
            showTimePickerDialog()
        }

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val annotation = binding.annotationEditText.text.toString()
            val location = binding.locationEditText.text.toString()

            if (title.isNotBlank() && annotation.isNotBlank() && location.isNotBlank()) {
                val entry = DiaryEntry(
                    title = title,
                    annotation = annotation,
                    location = location,
                    date = date,
                    time = time
                )
                viewModel.insert(entry)
                finish()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            binding.dateButton.text = date
        }, year, month, day).show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            time = "$selectedHour:$selectedMinute"
            binding.timeButton.text = time
        }, hour, minute, true).show()
    }
}
