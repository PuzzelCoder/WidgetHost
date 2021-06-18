package com.example.myhostwidget;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

//import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyHostWidget";
    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE =5874 ;
    AppWidgetManager mAppWidgetManager;
    AppWidgetHost mAppWidgetHost;
    RelativeLayout customWidget;
    final int REQUEST_BIND_WIDGET = 1987;
    private AppWidgetProviderInfo newAppWidgetProviderInfo;
    private AppWidgetHostView hostView;
    private int appWidgetId;

    /**
     * Called on the creation of the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        ComponentName receiver = new ComponentName(this, BootCompleteBroadcastReceiver.class);
        PackageManager pm = getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);

        customWidget = (RelativeLayout) findViewById(R.id.custom_layout);
        mAppWidgetManager = MyApplicationClass.getAppWidgetManager();
        mAppWidgetHost = MyApplicationClass.getAppWidgetHost();

        List<AppWidgetProviderInfo> infoList = mAppWidgetManager.getInstalledProviders();
        TextView textView = findViewById(R.id.textView);
        StringBuilder stringBuilder= new StringBuilder();
//        //get the curent active user
        for (AppWidgetProviderInfo info : infoList) {
            stringBuilder.append("Name: " + info.label);
            stringBuilder.append("\n");
            Log.d(TAG, "Name: " + info.label);
            Log.d(TAG, "Provider Name: " + info.provider);
            stringBuilder.append("provider: " + info.provider);
            stringBuilder.append("\n");
            Log.d(TAG, "Configure Name: " + info.configure);
            stringBuilder.append("configure: " + info.configure);
            stringBuilder.append("\n");
            Log.d(TAG, "widgetCategory: " + info.widgetCategory);
            stringBuilder.append("widgetCategory: " + info.widgetCategory);
            stringBuilder.append("\n");
            Log.d("========", "===========");

        }
        textView.setText(stringBuilder.toString());


    }
    private void RequestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!Settings.canDrawOverlays(this)) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                        Uri.parse("package:" + this.getPackageName()));
//                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
//            } else {
                //Permission Granted-System will work
                boolean widget = createBoolWidget(null, "com.example.tutorialspointwidget", "com.example.tutorialspointwidget.WidgetConfig");
//                boolean widget = createBoolWidget(null, "com.example.widgetlistview", "com.example.widgetlistview.WidgetProvider");
                if (widget) {
                    Log.d(TAG, "onCreate: widget there");
                } else {
                    Log.d(TAG, "onCreate: widget not there");
                }
//            }
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(hostView!=null)
        removeWidget(hostView);
        RequestPermission();
    }

    /**
     * If the user has selected an widget, the result will be in the 'data' when
     * this function is called.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BIND_WIDGET) {
            if (resultCode == RESULT_OK) {
                hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);
                customWidget.addView(hostView);
            }

            Log.d(TAG, "onActivityResult: " + resultCode);
        }
        else if(requestCode== ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE){
           if(resultCode == RESULT_OK){
               boolean widget = createBoolWidget(null, "com.example.tutorialspointwidget", "com.example.tutorialspointwidget.WidgetConfig");
               if (widget) {
                   Log.d(TAG, "onCreate: widget there");
               } else {
                   Log.d(TAG, "onCreate: widget not there");
               }
           }
           else{
               Log.d(TAG, "user refused ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE");
           }
        }  else{
            Log.d(TAG, "onActivityResult: " + requestCode);
        }

    }


    /**
     * Registers the AppWidgetHost to listen for updates to any widgets this app
     * has.
     */
    @Override
    protected void onStart() {
        super.onStart();
        mAppWidgetHost.startListening();

    }

    /**
     * Removes the widget displayed by this AppWidgetHostView.
     */
    public void removeWidget(AppWidgetHostView hostView) {
        if(mAppWidgetHost !=null){
            mAppWidgetHost.deleteAppWidgetId(hostView.getAppWidgetId());
        }
        customWidget.removeView(hostView);
    }


    @Override
    protected void onDestroy() {
        mAppWidgetHost.stopListening();
        mAppWidgetHost = null;
        removeWidget(hostView);
        super.onDestroy();
    }


    public boolean createBoolWidget(View view, String packageName, String className) {
        // Get the list of installed widgets
        List<AppWidgetProviderInfo> appWidgetInfos;
        appWidgetInfos = mAppWidgetManager.getInstalledProviders();
        boolean widgetIsFound = false;
        for (int j = 0; j < appWidgetInfos.size(); j++) {
            if (appWidgetInfos.get(j).provider.getPackageName().equals(packageName) && appWidgetInfos.get(j).provider.getClassName().equals(className)) {
                // Get the full info of the required widget
                newAppWidgetProviderInfo = appWidgetInfos.get(j);
                widgetIsFound = true;
                break;
            }
        }

        if (!widgetIsFound) {
            return false;
        } else {
            // Create Widget
            appWidgetId = mAppWidgetHost.allocateAppWidgetId();
            hostView = mAppWidgetHost.createView(getApplicationContext(), appWidgetId, newAppWidgetProviderInfo);
            boolean allowed = mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId, newAppWidgetProviderInfo.provider);
            Log.d(TAG, "createBoolWidget: "+allowed);
            if (!allowed) {
                // Request permission - https://stackoverflow.com/a/44351320/1816603
                Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, newAppWidgetProviderInfo.provider);

                startActivityForResult(intent, REQUEST_BIND_WIDGET);
            } else {
                hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);
                customWidget.addView(hostView);
            }

            return true;
        }
    }

}


