package vi1ain.my.notealarm3.note_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vi1ain.my.notealarm3.R
import vi1ain.my.notealarm3.alarm_manager.AlarmIntentManager
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.data.Str
import vi1ain.my.notealarm3.date_time_picker.DialogDatePicker
import vi1ain.my.notealarm3.date_time_picker.DialogTimePicker
import vi1ain.my.notealarm3.dialog_manager.DialogController
import vi1ain.my.notealarm3.navigation.Routes
import vi1ain.my.notealarm3.note_list.note_reading.NoteCardReading
import vi1ain.my.notealarm3.ui.theme.xDarkGreen
import vi1ain.my.notealarm3.ui.theme.xLightGreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteList(

    alarmIntentManager: AlarmIntentManager,
    navController: NavHostController,
    noteViewModel: NoteViewModel
) {
    val noteList = noteViewModel.noteList.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (noteViewModel.dialogState) DialogController(onDismissRequest = {
        noteViewModel.insertNotes(alarmIntentManager)
        noteViewModel.dialogState = false
    }, confirmButton = {
        noteViewModel.insertNotes(alarmIntentManager)
        noteViewModel.dialogState = false
    }, dismissButton = { noteViewModel.dialogState = false },
        noteViewModel = noteViewModel
    )
    //DATE_PICKER
    if (noteViewModel.openDialogDatePicker) DialogDatePicker(
        onDismissRequest = {
            noteViewModel.openDialogDatePicker = false
        },
        confirmButton = {
            noteViewModel.openDialogDatePicker = false
            noteViewModel.openDialogTimePicker = true
        },
        dismissButton = { noteViewModel.openDialogDatePicker = false },
        noteViewModel = noteViewModel
    )
    //TIME_PICKER
    if (noteViewModel.openDialogTimePicker) DialogTimePicker(
        alarmIntentManager = alarmIntentManager,
        onDismissRequest = {
            noteViewModel.openDialogTimePicker = false
        },
        confirmButton = {
            noteViewModel.openDialogTimePicker = false

        },
        dismissButton = { noteViewModel.openDialogTimePicker = false },
        noteViewModel = noteViewModel
    )

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }, floatingActionButton = {
        ExtendedFloatingActionButton(containerColor = xLightGreen, onClick = {
            noteViewModel.titleState = ""
            noteViewModel.descriptionState = ""
            noteViewModel.newNoteItem = null
            noteViewModel.dialogState = true
        }) {
            Icon(/*modifier = Modifier
                .clip(CircleShape)
                .background(color = xGreen)
                .padding(5.dp),*/
                tint = xDarkGreen,
                painter = painterResource(R.drawable.add_note),
                contentDescription = Str.ADD
            )
            Text(color = xDarkGreen, text = Str.ADD)
        }
    }) {
        LazyColumn(
            content = {
                items(noteList.value) { itemNote ->
                    NoteCard(
                        navController = navController,
                        scope = scope,
                        snackbarHostState = snackbarHostState,
                        alarmIntentManager = alarmIntentManager,
                        itemNote = itemNote,
                        noteViewModel = noteViewModel,
                        onClickEdit = { item ->
                            noteViewModel.newNoteItem = item
                            noteViewModel.titleState = item.title
                            noteViewModel.descriptionState = item.description
                        },
                        onClickDelete = { item -> noteViewModel.deleteNote(item) })
                }
            }, contentPadding = PaddingValues(bottom = 80.dp)
        )
    }
}
