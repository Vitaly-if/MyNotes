package com.example.mynotes.ui.screens.main

import com.example.mynotes.domain.OrderType
import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.usecases.NoteOrder

/**
 * @author Vitaly.N on 15.02.2023.
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
