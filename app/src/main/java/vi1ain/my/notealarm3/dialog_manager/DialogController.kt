package vi1ain.my.notealarm3.dialog_manager

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.data.Str
import vi1ain.my.notealarm3.ui.theme.xLightGreen
import vi1ain.my.notealarm3.ui.theme.xLightText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogController(
    onDismissRequest: () -> Unit,
    confirmButton: () -> Unit,
    dismissButton: () -> Unit,
    noteViewModel: NoteViewModel
) {
    AlertDialog(containerColor = xLightGreen,
        onDismissRequest = { onDismissRequest() },
        dismissButton = {
            TextButton(onClick = { dismissButton() }) {
            Text(
                text = Str.CANSEL
            )
        }
        },
        confirmButton = {TextButton(onClick = { confirmButton() }) {
            Text(
                text = if (noteViewModel.newNoteItem == null) Str.ADD else
                    Str.EDIT
            )
        } },
        title = {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = if (noteViewModel.newNoteItem == null) Str.NEW_NOTE else
                    Str.EDIT)
                TextField(maxLines =2,colors = TextFieldDefaults.textFieldColors(containerColor = xLightGreen),
                    label = { Text(text = Str.TITLE, color = xLightText )},
                    modifier = Modifier.fillMaxWidth(),
                    value = noteViewModel.titleState,
                    onValueChange = { textTitle -> noteViewModel.titleState = textTitle })
                TextField(maxLines =5,colors = TextFieldDefaults.textFieldColors(containerColor = xLightGreen),
                    label = { Text(text = Str.DESCRIPTION, color = xLightText )},
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    value = noteViewModel.descriptionState,
                    onValueChange = { textDescription-> noteViewModel.descriptionState = textDescription })
            }
        })

}