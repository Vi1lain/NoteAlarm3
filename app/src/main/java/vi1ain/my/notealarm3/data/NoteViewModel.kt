package vi1ain.my.notealarm3.data

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
var dialogState by mutableStateOf(false)
    var titleState by mutableStateOf("")
    var descriptionState by mutableStateOf("")

  //DATE_PICKER states
    var selectedYear by
    mutableIntStateOf(0) // or use  mutableStateOf(0)
    var selectedMonth by
    mutableIntStateOf(0) // or use  mutableStateOf(0)
    var selectedDay by
    mutableIntStateOf(0) // or use  mutableStateOf(0)
    var openDialogDatePicker by mutableStateOf(false)

    //TIME_PICKER states
    var selectedHour by
    mutableIntStateOf(0) // or use  mutableStateOf(0)
    var selectedMinute by
    mutableIntStateOf(0) // or use  mutableStateOf(0)
    var openDialogTimePicker by mutableStateOf(false)

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

    fun checkBoxNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.insertNote(noteEntity = noteEntity)}
}

