package vi1ain.my.notealarm3.date_time_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import vi1ain.my.notealarm3.data.NoteViewModel

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
        onDismissRequest = { /*TODO*/ },
        ){
        Column (modifier = Modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.3f)
            )
            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            TimePicker(state = timePickerState)
        }
    }
}