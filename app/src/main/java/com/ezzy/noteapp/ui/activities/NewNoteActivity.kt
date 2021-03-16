package com.ezzy.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.ActivityNewNoteBinding
import com.ezzy.noteapp.models.Note
import com.ezzy.noteapp.repository.NoteRepository
import com.ezzy.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewNoteActivity : AppCompatActivity() {

    private val TAG = "NewNoteActivity"

    private lateinit var binding : ActivityNewNoteBinding
    private lateinit var notesViewModel: NoteViewModel
    @Inject
    lateinit var noteRepository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesViewModel = NoteViewModel(application, noteRepository)



        binding.buttonAddNoteMain.setOnClickListener {
            val note = Note(
                null,
                binding.noteTitleTextview.text.toString(),
                binding.noteDescTextview.text.toString(),
                "#ffffff",
                System.currentTimeMillis()
            )

            notesViewModel.insertNote(note)
            finish()

            Log.d(TAG, "Note: $note ")
        }
    }
}