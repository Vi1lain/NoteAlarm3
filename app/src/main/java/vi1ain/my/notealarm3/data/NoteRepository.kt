package vi1ain.my.notealarm3.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


interface NoteRepository {

    fun getAllNotes(): Flow<List<NoteEntity>>

    suspend fun insertNote(noteEntity: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)
}