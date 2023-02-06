package com.example.mynotes.domain.usecases

import com.example.mynotes.domain.repository.NoteRepository
import javax.inject.Inject

class FetchAllNotesUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend operator fun invoke() = noteRepository.fetchAllNotes()

}