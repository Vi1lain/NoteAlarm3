package vi1ain.my.notealarm3.note_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import vi1ain.my.notealarm3.R
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.ui.theme.xBlue
import vi1ain.my.notealarm3.ui.theme.xDarkGreen
import vi1ain.my.notealarm3.ui.theme.xDarkText
import vi1ain.my.notealarm3.ui.theme.xGreen
import vi1ain.my.notealarm3.ui.theme.xLightGreen
import vi1ain.my.notealarm3.ui.theme.xLightText
import vi1ain.my.notealarm3.ui.theme.xPurple
import vi1ain.my.notealarm3.ui.theme.xRed
import vi1ain.my.notealarm3.ui.theme.xWhite


@Composable
fun NoteCard(noteViewModel: NoteViewModel) {
    ConstraintLayout(modifier = Modifier
        .clickable {
            //TODO
        }
        .padding(start = 3.dp, end = 3.dp, top = 18.dp)) {
        val (card, onAlarmButtom, offAlarmButtom, deleteButtom, checkBox) = createRefs()
        Card(colors = CardDefaults.cardColors(
            containerColor = xLightGreen,
        ), border = BorderStroke(1.dp, xDarkGreen), modifier = Modifier
            .fillMaxWidth()
            .constrainAs(card) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Title",
                    style = TextStyle(color = xDarkText),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Description",
                    style = TextStyle(color = xLightText),
                    fontSize = 12.sp
                )
                Row {
                    Text(
                        color = xBlue,
                        modifier = Modifier.weight(1f),
                        text = "14:55",
                        style = TextStyle(color = xDarkText),
                        fontSize = 10.sp
                    )
                    Text(
                        color = xBlue,
                        text = "12.01.2024 - 19:28",
                        style = TextStyle(color = xLightText),
                        fontSize = 10.sp
                    )

                }
                Divider(modifier = Modifier.fillMaxWidth(), color = xWhite)

            }
        }
        IconButton(modifier = Modifier
            .constrainAs(deleteButtom) {
                top.linkTo(card.top)
                end.linkTo(card.end)
                bottom.linkTo(card.top)

            }
            .padding(end = 15.dp)
            .size(35.dp), onClick = {
//TODO
        }) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = xRed)
                    .padding(5.dp),
                tint = xWhite,
            )

        }
        Checkbox(modifier = Modifier
            .constrainAs(checkBox) {
                top.linkTo(card.top)
                start.linkTo(card.start)
                end.linkTo(card.end)
                bottom.linkTo(card.top)
            }
            .clip(CircleShape)
            .size(30.dp)
            .background(color = xPurple),
            checked = true,
            onCheckedChange = {
                //TODO
            },

            colors = CheckboxDefaults.colors(

                uncheckedColor = xPurple,
                disabledCheckedColor = xWhite,
                checkedColor = xPurple,
                checkmarkColor = xWhite
            )
        )
        IconButton(modifier = Modifier
            .constrainAs(onAlarmButtom) {
                top.linkTo(card.top)
                end.linkTo(deleteButtom.start)
                bottom.linkTo(card.top)

            }
            .padding(end = 15.dp)
            .size(35.dp), onClick = {
            noteViewModel.openDialogDatePicker = true
        }) {
            Icon(
                painter = painterResource(id = R.drawable.noti_add),
                contentDescription = "addAlarm",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = xGreen)
                    .padding(5.dp),
                tint = xDarkText,
            )

        }
        IconButton(modifier = Modifier
            .constrainAs(offAlarmButtom) {
                top.linkTo(card.top)
                end.linkTo(onAlarmButtom.start)
                bottom.linkTo(card.top)

            }
            .padding(end = 15.dp)
            .size(35.dp), onClick = {
//TODO
        }) {
            Icon(
                painter = painterResource(id = R.drawable.noti_cansel),
                contentDescription = "canselAlarm",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = xLightGreen)
                    .padding(5.dp),
                tint = xDarkText,
            )

        }

    }
}