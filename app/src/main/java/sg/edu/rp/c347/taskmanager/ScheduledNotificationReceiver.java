package sg.edu.rp.c347.taskmanager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;

public class ScheduledNotificationReceiver extends BroadcastReceiver {
    int reqCode=12345;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("default","Default Channel",NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent i= new Intent(context,MainActivity.class);
        PendingIntent pIntent=PendingIntent.getActivity(context,reqCode,i,PendingIntent.FLAG_CANCEL_CURRENT);
//        Intent intent2=getIntent();
//        String name = intent2.getExtras().getString("name");
        String value=intent.getAction();
        String name=intent.getExtras().getString("name");

        NotificationCompat.Action action = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Launch Task Manager",
                pIntent).build();

        Intent intentreply = new Intent(context,
                ReplyActivity.class);
        PendingIntent pendingIntentReply = PendingIntent.getActivity
                (context, reqCode, intentreply,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput ri = new RemoteInput.Builder("status")
                .setLabel("Status report")
                .setChoices(new String [] {"Done", "Not yet"})
                .build();

        NotificationCompat.Action action2 = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Reply",
                pendingIntentReply)
                .addRemoteInput(ri)
                .build();


        NotificationCompat.WearableExtender extender = new
                NotificationCompat.WearableExtender();
        extender.addAction(action);
        extender.addAction(action2);

        //build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"default");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(name);


        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
// Attach the action for Wear notification created above
        builder.extend(extender);

        Notification n=builder.build();
        notificationManager.notify(123,n);


    }
}
