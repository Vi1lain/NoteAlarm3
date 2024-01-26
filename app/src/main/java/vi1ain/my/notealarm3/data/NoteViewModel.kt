package vi1ain.my.notealarm3.data

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
val noteList = repository.getAllNotes()
    var newNoteItem: NoteEntity? = null

    var titleState by mutableStateOf("")
    var descriptionState by mutableStateOf("")

    fun insertNotes() = viewModelScope.launch {
        if (titleState.isNotEmpty()) {
            val noteItem = newNoteItem?.copy(title = titleState, description = descriptionState)
                ?: NoteEntity(
                    title = titleState,
                    description = descriptionState,
                    timeOfCreation = "26.01.2024",//заглушка
                    isCheck = newNoteItem?.isCheck ?: false
                )
            repository.insertNote(noteEntity = noteItem)
            newNoteItem = null
            titleState = ""
            descriptionState = ""
        }
    }


    fun deleteNote(noteEntity: NoteEntity) =
        viewModelScope.launch { repository.deleteNote(noteEntity = noteEntity) }
}