package com.app.edu.ifsp.diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.edu.ifsp.diary.databinding.ActivityMainBinding
import com.app.edu.ifsp.diary.viewmodel.DiaryEntryViewModel
import com.app.edu.ifsp.diary.adapter.DiaryEntryAdapter
import com.app.edu.ifsp.diary.ui.AddDiaryEntryActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: DiaryEntryViewModel by viewModels()
    private lateinit var adapter : DiaryEntryAdapter
    private lateinit var binding : ActivityMainBinding

    private val addDiaryEntryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.allEntries.observe(this) { entries ->
                    adapter.submitList(entries)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = layoutInflater
        binding = ActivityMainBinding.inflate(inflater)

        setContentView(binding.root)
        adapter = DiaryEntryAdapter(
            onItemClicked = { entry ->
                val intent = Intent(this, AddDiaryEntryActivity::class.java).apply {
                    putExtra("entry_id", entry.id)
                }
                addDiaryEntryLauncher.launch(intent)
            },
            onDeleteClicked = { entry ->
                viewModel.deleteById(entry.id)

            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allEntries.observe(this) { entries ->
            Log.i("","Mostrando dados...")
            adapter.submitList(entries)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddDiaryEntryActivity::class.java)
            addDiaryEntryLauncher.launch(intent)
        }
    }
}
