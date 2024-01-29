package vi1ain.my.notealarm3.note_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vi1ain.my.notealarm3.R
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.data.Str
import vi1ain.my.notealarm3.date_time_picker.DialogDatePicker
import vi1ain.my.notealarm3.date_time_picker.DialogTimePicker
import vi1ain.my.notealarm3.dialog_manager.DialogController
import vi1ain.my.notealarm3.ui.theme.xDarkGreen
import vi1ain.my.notealarm3.ui.theme.xLightGreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteList(noteViewModel: NoteViewModel = viewModel()) {
    val noteList = noteViewModel.noteList.collectAsState(initial = emptyList())

    if (noteViewModel.dialogState) DialogController(
        onDismissRequest = { noteViewModel.dialogState = false },
        confirmButton = { noteViewModel.dialogState = false },
        dismissButton = { noteViewModel.dialogState = false },
        noteViewModel = noteViewModel
    )
    if (noteViewModel.openDialogDatePicker) DialogDatePicker(
        onDismissRequest = { noteViewModel.openDialogDatePicker = false },
        confirmButton = { noteViewModel.openDialogDatePicker = false
            noteViewModel.openDialogTimePicker = true
                        },
        dismissButton = { noteViewModel.openDialogDatePicker = false },
        noteViewModel = noteViewModel
    )
    if (noteViewModel.openDialogTimePicker) DialogTimePicker(
        onDismissRequest = { noteViewModel.openDialogTimePicker = false },
        confirmButton = { noteViewModel.openDialogTimePicker = false },
        dismissButton = { noteViewModel.openDialogTimePicker = false },
        noteViewModel = noteViewModel
    )

    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            containerColor = xLightGreen,
            onClick = {
                noteViewModel.dialogState = true
                //noteViewModel.titleState = ""
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
            content = { items(50) { itemNote -> NoteCard(noteViewModel = noteViewModel) } },
            contentPadding = PaddingValues(bottom = 80.dp)
        )
    }
}
