package vi1ain.my.notealarm3.alarm_manager

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import vi1ain.my.notealarm3.R

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("key") ?: return
        val itemID = intent.getIntExtra("keyID", -1) ?: return

        Log.d("MyLog", "Alarm message:$message")
        showNatification(context, "Make it Easy $message", "Alarm message:$message", myID = itemID)

    }

    @SuppressLint("ObsoleteSdkInt")
    private fun showNatification(context: Context, title: String, desc: String, myID: Int) {

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "message_channel"
        val channelName = "message_name"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(context, channelID)
            .setContentText(title)
            .setContentTitle(desc)
            .setSmallIcon(R.drawable.alarm_icon)
        manager.notify(myID, builder.build())

    }
}