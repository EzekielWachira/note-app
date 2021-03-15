package com.ezzy.noteapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.FragmentNoteBinding
import com.ezzy.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel : NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}