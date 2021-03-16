package com.ezzy.noteapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.ActivityNewNoteBinding

class NewNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}