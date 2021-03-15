package com.ezzy.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ezzy.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    app: Application,
    val noteRepository: NoteRepository
) : AndroidViewModel(app) {



}