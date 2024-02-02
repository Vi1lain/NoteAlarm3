package vi1ain.my.notealarm3.note_list.note_reading

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import vi1ain.my.notealarm3.R
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.navigation.Routes
import vi1ain.my.notealarm3.ui.theme.xDarkGreen
import vi1ain.my.notealarm3.ui.theme.xGreen
import vi1ain.my.notealarm3.ui.theme.xWhite


@Composable
fun NoteCardReading(noteViewModel: NoteViewModel, navController: NavHostController) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = xWhite,
        ),
        border = BorderStroke(1.dp, xDarkGreen),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            TextButton(modifier = Modifier.fillMaxWidth(),onClick = { navController.popBackStack(route = Routes.LIST_SCREEN, inclusive = false)}) {
                Icon(painter = painterResource(id = R.drawable.back_icon), contentDescription = "Back")
                Text(modifier = Modifier.weight(1f),
                    text = noteViewModel.titleState, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Divider(thickness = 2.dp, modifier = Modifier.fillMaxWidth(), color = xGreen)
            Text(
                textAlign = TextAlign.Left,
                fontSize = 14.sp,
                text = noteViewModel.descriptionState,
            )
        }
    }
}