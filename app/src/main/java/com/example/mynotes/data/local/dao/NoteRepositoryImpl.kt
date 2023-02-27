package com.example.mynotes.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteRepositoryImpl {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    fun fetchAllNotes(): Flow<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id=:idNote")
    suspend fun fetchNote(idNote: Int): Note

}