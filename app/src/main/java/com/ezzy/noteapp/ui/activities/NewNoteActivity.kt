package com.ezzy.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.get
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
    private lateinit var note : Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesViewModel = NoteViewModel(application, noteRepository)

        if (intent?.hasExtra("note") == true){
            note = intent?.extras?.get("note") as Note
            binding.noteTitleTextview.setText(note.title)
            binding.noteDescTextview.setText(note.description)
        }

        binding.newNoteFab.setOnClickListener {
            saveNote()
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionDraft -> {
                saveDraft()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveDraft(){
        showToast("Saving to draft")
    }

    private fun saveNote(){

        if (isEmpty(binding.noteTitleTextview.text.toString()) &&
                isEmpty(binding.noteDescTextview.toString())){
            showToast(getString(R.string.empty_string))
        }

        val note = Note(
                null,
                binding.noteTitleTextview.text.toString(),
                binding.noteDescTextview.text.toString(),
                "#ffffff",
                System.currentTimeMillis()
        )

        notesViewModel.insertNote(note)
        finish()
    }

    private fun isEmpty(string : String) : Boolean{
        return string.toString().isEmpty()
    }

    private fun showToast(message :  String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}