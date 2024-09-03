package com.app.edu.ifsp.diary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.edu.ifsp.diary.data.model.DiaryEntry
import com.app.edu.ifsp.diary.databinding.DiaryEntryItemBinding

class DiaryEntryAdapter(private val onItemClicked: (DiaryEntry) -> Unit,private val onDeleteClicked: (DiaryEntry) -> Unit) :
    RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder>() {

    private var entries = listOf<DiaryEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryEntryViewHolder {
        val binding = DiaryEntryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaryEntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryEntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    override fun getItemCount(): Int = entries.size

    fun submitList(entries: List<DiaryEntry>) {
        this.entries = entries
        notifyDataSetChanged()
    }

    inner class DiaryEntryViewHolder(private val binding: DiaryEntryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: DiaryEntry) {
            binding.titleTextView.text = entry.title
            binding.dateTextView.text = entry.date
            binding.root.setOnClickListener { onItemClicked(entry) }
            binding.deleteButton.setOnClickListener {
                onDeleteClicked(entry)
            }
        }
    }
}
