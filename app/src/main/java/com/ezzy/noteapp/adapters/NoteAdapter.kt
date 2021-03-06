package com.ezzy.noteapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ezzy.noteapp.R
import com.ezzy.noteapp.models.Note
import com.ezzy.noteapp.util.formatTimeToDate
import com.ezzy.noteapp.util.smartTruncate

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var onItemClickListener : ((Note) -> Unit)? = null

    val diffCallBack = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this,  diffCallBack)

    fun setOnClickListener(listener: (Note) -> Unit) {
        onItemClickListener = listener
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.note_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.textTitle).text = note.title.smartTruncate(30)
            findViewById<TextView>(R.id.textDescription).text = note.description.smartTruncate(100)
            findViewById<TextView>(R.id.textDatetime).text =
                note.creationTime?.formatTimeToDate(note.creationTime)
            setOnClickListener {
                onItemClickListener?.let {
                    it(note)
                }
            }
//            setBackgroundColor(Color.parseColor(note.noteColor.toString()))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}