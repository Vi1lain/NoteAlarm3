package vi1ain.my.notealarm3.note_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun NoteCard(itemNote: Int) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()){
        Card (modifier = Modifier.fillMaxWidth()){
            Text(text = "TEST $itemNote")
        }
    }
}