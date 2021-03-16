package com.ezzy.noteapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ezzy.noteapp.R
import com.ezzy.noteapp.databinding.FragmentNewNoteBinding
import com.ezzy.noteapp.models.Note
import com.ezzy.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private val TAG = "NewNoteFragment"
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

        val noteTitle = binding.noteTitleText.text.toString()
        val noteDescription = binding.noteDescText.text.toString()

        val note = Note(
                null,
                noteTitle,
                noteDescription,
                "#ff0000",
                System.currentTimeMillis()
        )

        binding.buttonAddNote.setOnClickListener {
            Log.d(TAG, "NOtest: $noteTitle")
            noteViewModel.insertNote(note)
//            findNavController().navigate(R.id.action_newNoteFragment_to_noteFragment)
        }

        return binding.root
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val noteTitle = requireView().findViewById<EditText>(R.id.textTitle).text.toString()!!
//        val noteDesc = requireView().findViewById<EditText>(R.id.textDescription).text.toString()!!
//        val note = Note(
//                null,
//                noteTitle,
//                noteDesc,
//                "#ffffff",
//                System.currentTimeMillis()
//        )
//
//        requireView().findViewById<Button>(R.id.buttonAddNote).setOnClickListener {
//            Log.d(TAG, "Note: $note ")
////            noteViewModel.insertNote(note)
////            Toast.makeText(activity, "Note added", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}