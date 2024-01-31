package vi1ain.my.notealarm3.utils

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun ViewModel.getCurrentTime(): String{
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    val vc = Calendar.getInstance()
    return formatter.format(vc.time)
}