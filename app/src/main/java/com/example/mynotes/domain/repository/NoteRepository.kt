package com.example.mynotes.domain.repository

import com.example.mynotes.data.local.dao.NoteRepositoryImpl
import com.example.mynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteRepositoryImpl: NoteRepositoryImpl) {

    suspend fun insertNote(note: Note) = noteRepositoryImpl.insertNote(note = note)

    fun fetchAllNotes(): Flow<List<Note>> = noteRepositoryImpl.fetchAllNotes()

    suspend fun deleteNote(note: Note) = noteRepositoryImpl.deleteNote(note = note)

    suspend fun fetchNote(idNote: Long) = noteRepositoryImpl.fetchNote(idNote = idNote)
}