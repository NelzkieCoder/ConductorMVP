package com.jshvarts.notedomain.usecases

import com.jshvarts.notedomain.model.Note
import com.jshvarts.notedomain.repository.NoteRepository
import io.reactivex.Completable

class AddNoteUseCase(private val repository: NoteRepository) {
    fun add(note: Note): Completable = validate(note).andThen(repository.insertOrUpdate(note))

    private fun validate(note: Note): Completable {
        return if (!note.isValidForAdd()) {
            Completable.error(IllegalArgumentException("note failed validation before add"))
        } else {
            Completable.complete()
        }
    }
}