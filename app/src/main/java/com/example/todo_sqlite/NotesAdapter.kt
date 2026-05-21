package com.example.todo_sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(var notesList: List<NoteModel>,var context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotesViewHolder,
        position: Int
    ) {

        val data = notesList[position]
        holder.title.text = data.title
        holder.desc.text  = data.description

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun refreshData(newNotes: List<NoteModel>) {
        notesList = newNotes
        notifyDataSetChanged()
    }


    class NotesViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val title = item.findViewById<TextView>(R.id.title)
        val desc = item.findViewById<TextView>(R.id.subTitle)
    }
}