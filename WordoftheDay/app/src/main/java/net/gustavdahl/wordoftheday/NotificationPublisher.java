package net.gustavdahl.wordoftheday;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.gustavdahl.wordoftheday.MainActivity;
import net.gustavdahl.wordoftheday.R;

public class NotificationPublisher extends BroadcastReceiver
{
public static String NOTIFICATION_ID = "Main-id";
public static String NOTIFICATION = "notification";

        public void onReceive(Context context, Intent intent) {

            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

            Notification notification = intent.getParcelableExtra(NOTIFICATION);
            int id = intent.getIntExtra(NOTIFICATION_ID, 0);
            notificationManager.notify(id, notification);

        }
}