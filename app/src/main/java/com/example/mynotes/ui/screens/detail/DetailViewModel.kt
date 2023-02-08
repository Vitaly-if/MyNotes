package com.example.mynotes.ui.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.usecases.DeleteNoteUseCase
import com.example.mynotes.domain.usecases.FetchNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Vitaly.N on 08.02.2023.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchNoteUseCase: FetchNote,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note


    fun fetchNote(id: Long) {
        viewModelScope.launch {
            fetchNoteUseCase.invoke(idNote = id).let {
                _note.postValue(it)
            }
        }
    }
fun deleteNote(onSuccess: () -> Unit) {
    viewModelScope.launch {
        note.value?.let {
        deleteNoteUseCase.invoke(note = it)
            onSuccess()
        }

    }
}
}