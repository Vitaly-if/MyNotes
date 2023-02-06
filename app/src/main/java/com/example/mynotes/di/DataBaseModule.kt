package com.example.mynotes.di

import android.content.Context
import androidx.room.Room
import com.example.mynotes.data.local.dao.AppDatabase
import com.example.mynotes.data.local.dao.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    fun provideAppData(@ApplicationContext appContect: Context): AppDatabase {
        return Room.databaseBuilder(
            appContect,
            AppDatabase::class.java,
            "note.database"
        ).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteRepositoryImpl {
        return appDatabase.noteDao()
    }
}