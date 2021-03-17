package com.ezzy.noteapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ezzy.noteapp.databinding.ActivityNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2).apply {
            isEnabled = false
        }

        binding.mainFab.setOnClickListener {
            startActivity(Intent(this, NewNoteActivity::class.java))
        }
    }
}