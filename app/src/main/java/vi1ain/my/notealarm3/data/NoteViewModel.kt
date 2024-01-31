package vi1ain.my.notealarm3.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vi1ain.my.notealarm3.alarm_manager.AlarmIntentManager
import vi1ain.my.notealarm3.utils.getCurrentTime
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    val noteList = repository.getAllNotes()
    var newNoteItem: NoteEntity? = null
    var dialogState by mutableStateOf(false)
    var titleState by mutableStateOf("")
    var descriptionState by mutableStateOf("")


    val calendar = Calendar.getInstance()

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

    fun insertNotes(alarmIntentManager: AlarmIntentManager) = viewModelScope.launch {
        if (titleState.isNotEmpty()) {
            val noteItem = newNoteItem?.copy(title = titleState, description = descriptionState)
                ?: NoteEntity(
                    title = titleState,
                    description = descriptionState,
                    timeOfCreation = getCurrentTime(),
                    isCheck = newNoteItem?.isCheck ?: false,
                    switch = newNoteItem?.switch ?: false,
                )
            repository.insertNote(noteEntity = noteItem)

            if (newNoteItem?.year != null) {
                newNoteItem = noteItem
                calendar.set(
                    newNoteItem!!.year!!,
                    newNoteItem!!.month!! - 1,
                    newNoteItem!!.day!!,
                    newNoteItem!!.hour!!,
                    newNoteItem!!.minutes!!
                )
                alarmIntentManager.schedule(newNoteItem!!, this@NoteViewModel)

            }

            newNoteItem = null
            titleState = ""
            descriptionState = ""
        }
    }


    fun deleteNote(noteEntity: NoteEntity) =
        viewModelScope.launch { repository.deleteNote(noteEntity = noteEntity) }

    fun checkBoxNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.insertNote(noteEntity = noteEntity)
    }
    fun switchNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.insertNote(noteEntity = noteEntity)
    }

    fun alarmTimeNote() = viewModelScope.launch {
        val item = newNoteItem?.copy(
            year = selectedYear,
            month = selectedMonth,
            day = selectedDay,
            hour = selectedHour,
            minutes = selectedMinute,
        )
        if (item != null) {
            repository.insertNote(noteEntity = item)
            newNoteItem = null
            titleState = ""
            descriptionState = ""
        }
    }

}

