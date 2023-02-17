package com.example.mynotes.ui

import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.usecases.NoteOrder

/**
 * @author Vitaly.N on 15.02.2023.
 */
sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}