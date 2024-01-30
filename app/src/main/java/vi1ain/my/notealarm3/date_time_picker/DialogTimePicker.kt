package vi1ain.my.notealarm3.date_time_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.ui.theme.xGreen
import vi1ain.my.notealarm3.ui.theme.xLightGreen
import vi1ain.my.notealarm3.ui.theme.xWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTimePicker(
    onDismissRequest: () -> Unit,
    dismissButton: () -> Unit,
    confirmButton: () -> Unit,
    noteViewModel: NoteViewModel,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = noteViewModel.selectedHour,
        initialMinute = noteViewModel.selectedMinute,
        is24Hour = true
    )
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = xLightGreen
                    //.copy(alpha = 0.3f)
                )
                .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = xWhite,
                    clockDialSelectedContentColor = xWhite,
                    timeSelectorSelectedContainerColor = xGreen,
                    timeSelectorUnselectedContainerColor =  xWhite
                )
            )

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { dismissButton() }) {
                    Text(text = "Dismiss")
                }
                TextButton(onClick = { confirmButton() }) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}