package com.ezzy.noteapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezzy.noteapp.adapters.NoteAdapter
import com.ezzy.noteapp.databinding.FragmentNoteBinding
import com.ezzy.noteapp.ui.activities.NewNoteActivity
import com.ezzy.noteapp.util.RecyclerViewItemDecorator
import com.ezzy.noteapp.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var noteAdapter: NoteAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        noteAdapter.setOnClickListener {
            val intent = Intent(activity, NewNoteActivity::class.java)
            intent.apply {
                putExtra("note", it)
                startActivity(this)
            }
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = noteAdapter.differ.currentList[position]
                noteViewModel.deleteNote(note)
                Snackbar.make(
                        view,
                        "Note deleted",
                        Snackbar.LENGTH_LONG
                ).apply {
                    setAction("UNDO"){
                        noteViewModel.insertNote(note)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.noteRecyclerView)
        }

        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            noteAdapter.differ.submitList(it)
        })

        var job : Job? = null
        binding.searchEditText.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                it?.let {
                    if (it.toString().isNotEmpty()){
                        searchNotes("%${it.toString()}%")
                    } else {
                        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer { notes ->
                            noteAdapter.differ.submitList(notes)
                        })
                    }
                }
            }
        }
    }

    private fun searchNotes(searchQuery : String){
        noteViewModel.searchNotes(searchQuery).observe(viewLifecycleOwner, Observer {
            noteAdapter.differ.submitList(it)
        })
    }

    private fun setUpRecyclerView(){
        noteAdapter = NoteAdapter()
        binding.noteRecyclerView.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(RecyclerViewItemDecorator(5))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}