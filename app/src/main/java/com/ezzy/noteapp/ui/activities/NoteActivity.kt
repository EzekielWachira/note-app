package com.ezzy.noteapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.ActivityNotesBinding
import com.ezzy.noteapp.ui.fragments.NewNoteFragment
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