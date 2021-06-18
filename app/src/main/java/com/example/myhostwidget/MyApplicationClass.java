package com.example.myhostwidget;

import android.app.Application;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.Context;

public class MyApplicationClass extends Application {

    private static final int HARDCODED_ID = 0;

    private static AppWidgetHost appWidgetHost;
    private static AppWidgetManager appWidgetManager;

    @Override
    public void onCreate() {
        super.onCreate();

        appWidgetManager = getAppWidgetManagerInstance(this);

        appWidgetHost = new AppWidgetHost(getApplicationContext(), HARDCODED_ID);
        appWidgetHost.startListening();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        appWidgetHost.stopListening();
        appWidgetHost = null;
    }

    public static AppWidgetHost getAppWidgetHost() { return appWidgetHost; }

    public static AppWidgetManager getAppWidgetManagerInstance(Context context) {
        return (AppWidgetManager) context.getSystemService(Context.APPWIDGET_SERVICE);
    }
    public static AppWidgetManager getAppWidgetManager() { return appWidgetManager; }

}