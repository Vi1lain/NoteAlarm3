package vi1ain.my.notealarm3.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vi1ain.my.notealarm3.alarm_manager.AlarmIntentManager
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.note_list.NoteList
import vi1ain.my.notealarm3.note_list.note_reading.NoteCardReading


object Routes{
    const val LIST_SCREEN = "listScreen"
    const val NOTE_READLING = "NoteReadling"
}
@Composable
fun MyNavigation(noteViewModel: NoteViewModel = viewModel(), alarmIntentManager: AlarmIntentManager) {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = Routes.LIST_SCREEN ){
        composable(route =Routes.LIST_SCREEN){ NoteList(noteViewModel=noteViewModel,navController = navController,alarmIntentManager =alarmIntentManager )}
        composable(Routes.NOTE_READLING){ NoteCardReading(noteViewModel=noteViewModel)}
    }
}