package vi1ain.my.notealarm3.note_list

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import vi1ain.my.notealarm3.R
import vi1ain.my.notealarm3.alarm_manager.AlarmIntentManager
import vi1ain.my.notealarm3.data.NoteEntity
import vi1ain.my.notealarm3.data.NoteViewModel
import vi1ain.my.notealarm3.data.Str
import vi1ain.my.notealarm3.navigation.Routes
import vi1ain.my.notealarm3.ui.theme.xBlue
import vi1ain.my.notealarm3.ui.theme.xDarkGreen
import vi1ain.my.notealarm3.ui.theme.xDarkText
import vi1ain.my.notealarm3.ui.theme.xGreen
import vi1ain.my.notealarm3.ui.theme.xLightGreen
import vi1ain.my.notealarm3.ui.theme.xLightText
import vi1ain.my.notealarm3.ui.theme.xPurple
import vi1ain.my.notealarm3.ui.theme.xRed
import vi1ain.my.notealarm3.ui.theme.xSilver
import vi1ain.my.notealarm3.ui.theme.xText
import vi1ain.my.notealarm3.ui.theme.xWhite


@SuppressLint("RememberReturnType")
@Composable
fun NoteCard(
    onClickEdit: (NoteEntity) -> Unit,
    onClickDelete: (NoteEntity) -> Unit,
    noteViewModel: NoteViewModel,
    itemNote: NoteEntity,
    alarmIntentManager: AlarmIntentManager,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController,
) {


    ConstraintLayout(
        modifier = Modifier

            .padding(start = 3.dp, end = 3.dp, top = 20.dp)
    ) {
        val (card, onAlarmButtom, editButtom, switch, deleteButtom, checkBox) = createRefs()
        Card(colors = CardDefaults.cardColors(
            containerColor = xLightGreen,
        ), border = BorderStroke(0.5.dp, xGreen), modifier = Modifier
            .clickable {
                noteViewModel.titleState = itemNote.title
                noteViewModel.descriptionState = itemNote.description
                navController.navigate(Routes.NOTE_READLING)
            }
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
                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 5.dp),
                    text = itemNote.title,
                    style = TextStyle(color = xDarkText),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    text = itemNote.description,
                    style = TextStyle(color = xText),
                    fontSize = 12.sp
                )
                Row {
                    Text(
                        color = xBlue,
                        modifier = Modifier.weight(1f),
                        text = itemNote.timeOfCreation,
                        style = TextStyle(color = xDarkText),
                        fontSize = 10.sp
                    )
                    Text(
                        modifier = Modifier.padding(end = 40.dp),
                        color = xBlue,
                        text = if (itemNote.year != null)
                            "напомнить - ${"%02d".format(itemNote.day)}.${"%02d".format(itemNote.month)}.${itemNote.year} " +
                                    "в - ${itemNote.hour}:${"%02d".format(itemNote.minutes)}"
                        else "",
                        style = TextStyle(color = xLightText),
                        fontSize = 10.sp
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = xWhite)
            }
        }
        IconButton(modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 30.dp,
                )
            )
            .background(color = xGreen)
            .size(40.dp)
            .constrainAs(editButtom) {
                top.linkTo(card.top)
                end.linkTo(card.end)
                //bottom.linkTo(card.bottom)

            },
            onClick = {
                onClickEdit(itemNote)
                noteViewModel.dialogState = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = Str.EDIT,
                modifier = Modifier
                    .size(40.dp)


                    .padding(5.dp),
                tint = xWhite,
            )
        }
        IconButton(modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 0.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 0.dp,
                )
            )
            .background(color = xRed)
            .size(40.dp)
            .constrainAs(deleteButtom) {
                //top.linkTo(card.top)
                end.linkTo(card.end)
                bottom.linkTo(card.bottom)

            }
            //.padding(end = 15.dp)
            // .size(45.dp)
            , onClick = {
                scope.launch {
                    val result = snackbarHostState
                        .showSnackbar(
                            message = itemNote.title,
                            actionLabel = "Восстановить",
                            duration = SnackbarDuration.Short
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            noteViewModel.snackBarItem(itemNote)
                        }

                        SnackbarResult.Dismissed -> {
                            /* Handle snackbar dismissed */
                        }
                    }
                    /*if (result == SnackbarResult.ActionPerformed) {
                        noteViewModel.snackBarItem(itemNote)
                    }*/
                }
                alarmIntentManager.cansel(itemNote)
                onClickDelete(itemNote)
            }) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = Str.DELETE,
                modifier = Modifier
                    .size(40.dp)


                    .padding(5.dp),
                tint = xWhite,
            )
        }
        Checkbox(modifier = Modifier
            .constrainAs(checkBox) {
                top.linkTo(card.top)
                start.linkTo(card.start)
                //end.linkTo(card.start)
                //bottom.linkTo(card.top)
            }
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 0.dp,
                    bottomEnd = 30.dp,
                    bottomStart = 0.dp,
                )
            )
            .size(40.dp)
            .background(color = xPurple),
            checked = itemNote.isCheck,
            onCheckedChange = { check ->
                noteViewModel.checkBoxNote(
                    itemNote.copy(

                        isCheck = check
                    )
                )
            },
            colors = CheckboxDefaults.colors(

                uncheckedColor = xPurple,
                disabledCheckedColor = xWhite,
                checkedColor = xPurple,
                checkmarkColor = xWhite
            )
        )

        Row(modifier = Modifier
            .padding(end = 45.dp)
            .clip(CircleShape)
            .background(color = xPurple)
            .constrainAs(onAlarmButtom) {
                top.linkTo(card.top)
                end.linkTo(card.end)
                bottom.linkTo(card.top)
            }) {


            IconButton(
                modifier = Modifier
                    /*.constrainAs(offAlarmButtom) {
                        top.linkTo(card.top)
                        end.linkTo(onAlarmButtom.start)
                        bottom.linkTo(card.top)
                    }*/
                    //.padding(end = 15.dp)
                    //.size(35.dp)
                    //.padding(end = 60.dp)
                    .size(35.dp), onClick = {
                    noteViewModel.deleteAlarmCheckNote(
                        itemNote.copy(
                            year = null,
                            month = null,
                            day = null,
                            hour = null,
                            minutes = null,
                            alarmIsCheck = false
                        )
                    )
                    alarmIntentManager.cansel(itemNote)
                }, enabled = itemNote.alarmIsCheck
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.noti_cansel),
                    contentDescription = Str.ADD_ALARM,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = if (itemNote.alarmIsCheck) xRed else xSilver)
                        .padding(5.dp),
                    tint = xWhite,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(modifier = Modifier
                /* .constrainAs(onAlarmButtom) {
                    top.linkTo(card.top)
                    end.linkTo(card.end)
                    bottom.linkTo(card.top)
                }*/
                // .padding(end = 60.dp)
                .size(35.dp), onClick = {
                noteViewModel.newNoteItem = itemNote
                noteViewModel.openDialogDatePicker = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.noti_add),
                    contentDescription = Str.ADD_ALARM,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = xGreen)
                        .padding(5.dp),
                    tint = xDarkText,
                )
            }
        }
    }
}