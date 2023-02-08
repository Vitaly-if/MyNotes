package com.example.mynotes.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.usecases.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Vitaly.N on 08.02.2023.
 */
@HiltViewModel
class AddVieweModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {
    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            addNoteUseCase.invoke(note = note)
            onSuccess()
        }
    }
}