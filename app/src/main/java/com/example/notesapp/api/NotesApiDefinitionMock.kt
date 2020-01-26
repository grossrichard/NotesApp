package com.example.notesapp.api

import com.example.notesapp.api.dto.NoteDto
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * Created by Richard Gross on 2020-01-19
 */
class NotesApiDefinitionMock : NotesApiDefinition {

    companion object {
        private const val MOCK_RESPONSE_DURATION_MILLIS = 2000L
    }

    override fun loadNotes(): Single<List<NoteDto>> {
        return Single.just(MockDataProvider.createMockLoadRecipesResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun addNote(note: NoteDto): Single<NoteDto> {
        return Single.just(note)
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun updateNote(id: String, note: NoteDto): Single<NoteDto> {
        return Single.just(note)
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun deleteNote(id: String) {
        // todo implement
    }

    override fun loadNoteDetail(id: String): Single<NoteDto> =
        Single.just(MockDataProvider.createLoadRecipeDetailResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)

}