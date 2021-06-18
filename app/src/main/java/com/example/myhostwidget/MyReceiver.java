package com.example.myhostwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
//        if(intent.getAction().equals("com.example.widget.RandomWidget.updated")){
            String s=intent.getStringExtra("milliseconds");
            Log.d(TAG, "onReceive: "+s);
            // Construct the RemoteViews object
//                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_widget);

//                views.setTextViewText(R.id.appwidget_text2, s);
//        }

    }
}