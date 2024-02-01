package vi1ain.my.notealarm3.alarm_manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import vi1ain.my.notealarm3.data.NoteEntity
import vi1ain.my.notealarm3.data.NoteViewModel

class AlarmIntentManager(private var context: Context) : AlarmScheduler {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun schedule(item: NoteEntity, noteViewModel: NoteViewModel) {
        val alarmIntent =
            Intent(context, MyReceiver::class.java).let { intent ->
                intent.putExtra("key", item.title)
                intent.putExtra("keyID", item.id)
                PendingIntent.getBroadcast(
                    context,
                    item.id!!,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT

                )
            }
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            noteViewModel.calendar.timeInMillis,
            alarmIntent
        )
    }

    override fun cansel(item: NoteEntity) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.id!!,
                Intent(context, MyReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT

            )
        )
    }

}