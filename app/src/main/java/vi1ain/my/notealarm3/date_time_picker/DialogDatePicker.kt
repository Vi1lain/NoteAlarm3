package vi1ain.my.notealarm3.date_time_picker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.data.Str
import vi1ain.my.notealarm3.ui.theme.xLightGreen
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDatePicker(
    onDismissRequest: () -> Unit,
    confirmButton: () -> Unit,
    dismissButton: () -> Unit,
    noteViewModel: NoteViewModel
) {
    val datePickerState =
        rememberDatePickerState(
            initialSelectedDateMillis = Instant.now().toEpochMilli()
        )//устанавливает текущую дату в календаре

    val datePickerMillis = datePickerState.selectedDateMillis


    datePickerMillis?.let {

        val instant = Instant.ofEpochMilli(datePickerMillis).atZone(ZoneId.of("UTC")).toLocalDate()
        noteViewModel.selectedDay = instant.dayOfMonth
        noteViewModel.selectedMonth = instant.monthValue
        noteViewModel.selectedYear = instant.year

    }

    DatePickerDialog(colors = DatePickerDefaults.colors(containerColor = xLightGreen),
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { confirmButton() }) {
                Text(Str.OK)
            }
        },
        dismissButton = {
            TextButton(onClick = { dismissButton() }) {
                Text(Str.CANSEL)
            }
        }) {
        DatePicker(state = datePickerState, dateValidator = { timesTamp ->
            val selectedDate = Instant
                .ofEpochMilli(timesTamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val currentDate = LocalDate.now(ZoneId.systemDefault())
            selectedDate >= currentDate
        })
    }
}