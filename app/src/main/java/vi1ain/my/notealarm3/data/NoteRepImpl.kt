package vi1ain.my.notealarm3.data

import kotlinx.coroutines.flow.Flow

class NoteRepImpl(private val dao: NoteDao) : NoteRepository {
    override fun getAllNotes(): Flow<List<NoteEntity>> = dao.getAllNotes()

    override suspend fun insertNote(noteEntity: NoteEntity) = dao.insertNote(noteEntity)

    override suspend fun deleteNote(noteEntity: NoteEntity) = dao.deleteNote(noteEntity)
}