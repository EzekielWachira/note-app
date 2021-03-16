package com.ezzy.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.ActivityNewNoteBinding
import com.ezzy.noteapp.models.Note

class NewNoteActivity : AppCompatActivity() {

    private val TAG = "NewNoteActivity"

    private lateinit var binding : ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val note = Note(
            null,
            binding.noteTitleTextview.text.toString(),
            binding.noteDescTextview.text.toString(),
            "#ffffff",
            System.currentTimeMillis()
        )

        binding.buttonAddNoteMain.setOnClickListener {
            Log.d(TAG, "Note: $note ")
        }
    }
}