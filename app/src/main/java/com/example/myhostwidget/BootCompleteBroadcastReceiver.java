package com.example.myhostwidget;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class BootCompleteBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG="BootCompleteBroadcastReceiver";
    private static final String TAG_NOTIFICATION = "NOTIFICATION_MESSAGE";
    private static final String CHANNEL_ID = "channel_1111";
    private static final int NOTIFICATION_ID = 111111;

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (intent == null || intent.getExtras() == null)
//                          return;

//                 if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//                     Log.d(TAG, "android.intent.action.BOOT_COMPLETED received");
//                     Intent activity = new Intent(context, MainActivity.class);
//                     activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                     context.startActivity(activity);
//                      } else {
//                     Log.d(TAG, "Received unexpected intent " + intent.toString());
//                      }
//    }


            @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: "+intent.getAction());
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")
            || (intent.getAction().equals("android.intent.action.ACTION_BOOT_COMPLETED"))
            || (intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON"))
            ) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: ");

                try {

                    // If android 10 or higher
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)
                    {

                        startActivityNotification(context,NOTIFICATION_ID,context.getResources().getString(R.string.open_app), context.getResources().getString(R.string.click_app));

                    }
                    else
                    {
                        // If lower than Android 10, we use the normal method ever.
                        Intent activity = new Intent(context, MainActivity.class);
                        activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(activity);
                    }

                } catch (Exception e)
                {
                    Log.d(TAG,e.getMessage()+"");
                }
            }
//
        }


    // notification method to support opening activities on Android 10
    public static void startActivityNotification(Context context, int notificationID,
                                                 String title, String message) {

        NotificationManager mNotificationManager =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Create GPSNotification builder
        NotificationCompat.Builder mBuilder;

        //Initialise ContentIntent
        Intent ContentIntent = new Intent(context, MainActivity.class);
        ContentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent ContentPendingIntent = PendingIntent.getActivity(context,
                0,
                ContentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setAutoCancel(true)
                .setContentIntent(ContentPendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID,
                    "Activity Opening Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setDescription("Activity opening notification");

            mBuilder.setChannelId(CHANNEL_ID);

            Objects.requireNonNull(mNotificationManager).createNotificationChannel(mChannel);
        }

        Objects.requireNonNull(mNotificationManager).notify(TAG_NOTIFICATION,notificationID,
                mBuilder.build());
    }
    }


