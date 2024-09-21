package com.rishav.notsy.di.usecasemodule

import com.rishav.notsy.domain.repository.NoteRepository
import com.rishav.notsy.domain.usecase.AddNoteUseCase
import com.rishav.notsy.domain.usecase.DeleteNoteUseCase
import com.rishav.notsy.domain.usecase.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetNotesUseCase(noteRepository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideAddNoteUseCase(noteRepository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(noteRepository)
    }
}
