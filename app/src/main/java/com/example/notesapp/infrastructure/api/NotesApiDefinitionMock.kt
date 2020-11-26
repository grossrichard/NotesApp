package com.example.notesapp.infrastructure.api

import com.example.notesapp.infrastructure.api.dto.EmptyDto
import com.example.notesapp.infrastructure.api.dto.NoteDto
import io.reactivex.Single
import retrofit2.Response
import java.util.concurrent.TimeUnit

/**
 * Created by Richard Gross on 2020-01-19
 */
class NotesApiDefinitionMock : NotesApiDefinition {

    companion object {
        private const val MOCK_RESPONSE_DURATION_MILLIS = 2000L
    }

    override fun loadNotes(): Single<List<NoteDto>> {
        return Single.just(MockDataProvider.createMockLoadNotesResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun createNote(note: NoteDto): Single<NoteDto> {
        return Single.just(note)
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun updateNote(id: String, note: NoteDto): Single<NoteDto> {
        return Single.just(note)
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)
    }

    override fun deleteNote(id: String): Single<Response<EmptyDto>> =
        Single.just(Response.success(EmptyDto()))
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)

    override fun loadNoteDetail(id: String): Single<NoteDto> =
        Single.just(MockDataProvider.createLoadNoteDetailResp())
            .delay(MOCK_RESPONSE_DURATION_MILLIS, TimeUnit.MILLISECONDS)

}