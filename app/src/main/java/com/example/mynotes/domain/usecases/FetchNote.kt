package com.example.mynotes.domain.usecases

import com.example.mynotes.domain.repository.NoteRepository
import javax.inject.Inject

class FetchNote @Inject constructor(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(idNote: Long) = noteRepository.fetchNote(idNote = idNote)
}