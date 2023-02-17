package com.example.mynotes.domain.usecases

import com.example.mynotes.domain.InvalidNoteException
import com.example.mynotes.domain.model.Note
import com.example.mynotes.domain.repository.NoteRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class AddNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        if (note.title.isBlank())
            throw InvalidNoteException("The title of the note can't be empty")
        if (note.content.isBlank())
            throw InvalidNoteException("The content of the note can't be empty")


        noteRepository.insertNote(note = note)
    }
}