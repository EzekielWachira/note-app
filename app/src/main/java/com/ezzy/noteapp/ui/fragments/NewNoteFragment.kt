package com.ezzy.noteapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.FragmentNewNoteBinding
import com.ezzy.noteapp.models.Note
import com.ezzy.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment : Fragment() {

    private var _binding : FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(
            inflater, container, false
        )

        val note = Note(
            null,
            binding.noteTitleText.text.toString(),
            binding.noteDescText.text.toString(),
            "#ffffff",
            System.currentTimeMillis()
        )

        binding.buttonAddNote.setOnClickListener {
            noteViewModel.insertNote(note)
            findNavController().navigate(R.id.action_newNoteFragment_to_noteFragment)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}