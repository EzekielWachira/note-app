package com.ezzy.noteapp.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

//    private lateinit var isEditMode: Boolean
    private var isEditMode: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        notesViewModel = NoteViewModel(application, noteRepository)

        if (intent?.hasExtra("note") == true){
            note = intent?.extras?.get("note") as Note
            binding.noteTitleTextview.setText(note.title)
            binding.noteDescTextview.setText(note.description)
            notesViewModel.isEditMode.value = true
        }

        binding.newNoteFab.setOnClickListener {
            if (isEditMode == true){
                updateNote()
            } else {
                saveNote()
            }
        }

        notesViewModel.isEditMode.observe(this, Observer<Boolean> {
            if (it){
                binding.newNoteFab.setImageResource(R.drawable.ic_update)
                isEditMode = it
            }
        })

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

        if (!isEmpty(binding.noteTitleTextview.text.toString()) &&
                !isEmpty(binding.noteDescTextview.toString())){
            val note = Note(
                    null,
                    binding.noteTitleTextview.text.toString(),
                    binding.noteDescTextview.text.toString(),
                    "#ffffff",
                    System.currentTimeMillis()
            )

            notesViewModel.insertNote(note)
            finish()
        } else showToast(getString(R.string.empty_string))
    }

    private fun updateNote(){
        val newNote = Note(
            null,
            binding.noteTitleTextview.text.toString(),
            binding.noteDescTextview.text.toString(),
            "#ffffff",
            System.currentTimeMillis()
        )
        notesViewModel.updateNote(newNote)
//        notesViewModel.deleteNote(note)
//        notesViewModel.insertNote(newNote)
        showToast("Note updated")
        finish()
    }

    private fun isEmpty(string : String) : Boolean{
        return string.toString().isEmpty()
    }

    private fun showToast(message :  String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}