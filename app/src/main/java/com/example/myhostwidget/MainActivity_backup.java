package com.example.myhostwidget;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

//import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_backup extends AppCompatActivity {


    private static final String TAG = "MyHostWidget";
    AppWidgetManager mAppWidgetManager;
    AppWidgetHost mAppWidgetHost;
    private TextView textview;

    RelativeLayout mainlayout, customWidget;

    private static final int APPWIDGET_HOST_ID = 0x9345;
    private static final int APPWIDGET_HOST_ID_2 = 0x9346;
    private static final int REQUEST_CREATE_APPWIDGET = 0x9344;
    private static final int REQUEST_PICK_APPWIDGET = 0x9343;
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
        setContentView(R.layout.activity_main_backup);

        mainlayout = (RelativeLayout) findViewById(R.id.main_layout);
        customWidget = (RelativeLayout) findViewById(R.id.custom_layout);
        textview = (TextView) findViewById(R.id.textview);
        mAppWidgetManager= MyApplicationClass.getAppWidgetManager();
        mAppWidgetHost=MyApplicationClass.getAppWidgetHost();

//        mAppWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
//        mAppWidgetHost = new AppWidgetHost(getApplicationContext(), APPWIDGET_HOST_ID);

//        List<AppWidgetProviderInfo> infoList = mAppWidgetManager.getInstalledProvidersForPackage("com.example.tutorialspointwidget", null);
        List<AppWidgetProviderInfo> infoList = mAppWidgetManager.getInstalledProviders();

//        //get the curent active user
        for (AppWidgetProviderInfo info : infoList) {
            Log.d(TAG, "Name: " + info.label);
            Log.d(TAG, "Provider Name: " + info.provider);
            Log.d(TAG, "Configure Name: " + info.configure);
            Log.d(TAG, "widgetCategory: " + info.widgetCategory);
            Log.d("========", "===========");
        }
//
//// Create Widget
//        AppWidgetHostView hostView = mAppWidgetHost.createView(MainActivity.this, appWidgetId, newAppWidgetProviderInfo);
//        hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);
//
//// Add it to your layout
//        mainlayout.addView(hostView);
//        myText.setVisibility(View.GONE);

//        addWidget();
        createBoolWidget(null,"com.example.tutorialspointwidget","com.example.tutorialspointwidget.WidgetConfig");
    }

//    private void addWidget() {
//
//        AppWidgetHost mAppWidgetHost = new AppWidgetHost(this, APPWIDGET_HOST_ID_2);
////// Get an id
////        int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
//
//// Get the list of installed widgets
//        List<AppWidgetProviderInfo> appWidgetInfos = new ArrayList<AppWidgetProviderInfo>();
//        appWidgetInfos = mAppWidgetManager.getInstalledProviders();
//         newAppWidgetProviderInfo = null;
//        for (int j = 0; j < appWidgetInfos.size(); j++) {
////            if (appWidgetInfos.get(j).provider.getPackageName().equals("com.harman.ignite.mapbox")
////                    && appWidgetInfos.get(j).provider.getClassName().equals("com.harman.ignitenav.common.TBTWidget"))
//
//            if (appWidgetInfos.get(j).provider.getClassName().equals("com.example.tutorialspointwidget.WidgetConfig")) {
//                // Get the full info of the required widget
//                newAppWidgetProviderInfo = appWidgetInfos.get(j);
//                break;
//            }
//        }
//
//// Create Widget
////        if (newAppWidgetProviderInfo != null) {
////            AppWidgetHostView hostView = mAppWidgetHost.createView(getApplicationContext(), appWidgetId, newAppWidgetProviderInfo);
////            hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);
////            // Add it to your layout
////            customWidget.addView(hostView);
////        }
//
//        if(newAppWidgetProviderInfo != null){
//
////            int id = mAppWidgetHost.allocateAppWidgetId();
//            addProvider(this,mAppWidgetHost,mAppWidgetManager,newAppWidgetProviderInfo);
//
////            boolean success = false;
////            success = mAppWidgetManager.bindAppWidgetIdIfAllowed(id, newAppWidgetProviderInfo.provider);
////
////            if (success) {
////                Log.d(TAG, "addWidget: "+success);
////                AppWidgetHostView hostView = mAppWidgetHost.createView(getApplicationContext(), id, newAppWidgetProviderInfo);
////                AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(id);
////            hostView.setAppWidget(id, newAppWidgetProviderInfo);
//////            // Add it to your layout
////            customWidget.addView(hostView);
//////                LauncherAppWidgetInfo info = new LauncherAppWidgetInfo(id);
//////                info.hostView = hostView;
//////                info.hostView.setAppWidget(id, appWidgetInfo);
//////                attachWidget(info);
////            }
////            else {
////                Log.d(TAG, "addWidget: fail");
////                globalInt=id;
////                Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
////                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
////                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER,
////                        newAppWidgetProviderInfo.provider);
////                // TODO: we need to make sure that this accounts for the options
////                // bundle.
////                // intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_OPTIONS,
////                // options);
////                startActivityForResult(intent, 123);
////            }
//
//        }
//    }
//    public void addProvider(MainActivity m, AppWidgetHost host, AppWidgetManager manager, AppWidgetProviderInfo provider) {
//        int id = host.allocateAppWidgetId();
//        boolean success;
//
//        Bundle opts = new Bundle();
//        opts.putInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 1000);
//        opts.putInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 100);
//        opts.putInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, 200);
//        opts.putInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, 200);
//
//        success = manager.bindAppWidgetIdIfAllowed(id, provider.provider,opts);
//        Log.d(TAG, "addProvider: 1");
//        if (success) {
//            AppWidgetHostView hostView = host.createView(m, id, provider);
//            AppWidgetProviderInfo appWidgetInfo = manager.getAppWidgetInfo(id);
//            Log.d(TAG, "addProvider: 2");
//            hostView.setAppWidget(id, appWidgetInfo);
//            customWidget.addView(hostView);
//
//        } else {
//            Log.d(TAG, "addProvider: 4");
//            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
//            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
//            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, provider.provider);
//            m.startActivityForResult(intent, 123);
//        }
//
//    }


    /**
     * Launches the menu to select the widget. The selected widget will be on
     * the result of the activity.
     */
    void selectWidget() {

        int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
        Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
        pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER,newAppWidgetProviderInfo);

//        pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, componentName);
        addEmptyData(pickIntent);
        startActivityForResult(pickIntent, REQUEST_PICK_APPWIDGET);
    }
    /**
     * This avoids a bug in the com.android.settings.AppWidgetPickActivity,
     * which is used to select widgets. This just adds empty extras to the
     * intent, avoiding the bug.
     *
     * See more: http://code.google.com/p/android/issues/detail?id=4272
     */
    void addEmptyData(Intent pickIntent) {
        ArrayList<AppWidgetProviderInfo> customInfo = new ArrayList<AppWidgetProviderInfo>();
        pickIntent.putParcelableArrayListExtra(AppWidgetManager.EXTRA_CUSTOM_INFO, customInfo);
        ArrayList<Bundle> customExtras = new ArrayList<Bundle>();
        pickIntent.putParcelableArrayListExtra(AppWidgetManager.EXTRA_CUSTOM_EXTRAS, customExtras);
    }

    /**
     * If the user has selected an widget, the result will be in the 'data' when
     * this function is called.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_BIND_WIDGET){
            if (resultCode == RESULT_OK) {
                AppWidgetHostView hostView = mAppWidgetHost.createView(getApplicationContext(), appWidgetId, newAppWidgetProviderInfo);
                AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId);
                hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);
//            // Add it to your layout
                customWidget.addView(hostView);
            }

            Log.d(TAG, "onActivityResult: "+resultCode);
        }else{
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_PICK_APPWIDGET) {
                    configureWidget(data);
                } else if (requestCode == REQUEST_CREATE_APPWIDGET) {
                    createWidget(data);
                }
            } else if (resultCode == RESULT_CANCELED && data != null) {
                int appWidgetId = data.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
                if (appWidgetId != -1) {
                    mAppWidgetHost.deleteAppWidgetId(appWidgetId);
                }
            }
        }

    }

    /**
     * Checks if the widget needs any configuration. If it needs, launches the
     * configuration activity.
     */
    private void configureWidget(Intent data) {
        Bundle extras = data.getExtras();
        int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
        AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId);
        if (appWidgetInfo.configure != null) {
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
            intent.setComponent(appWidgetInfo.configure);
            Log.d(TAG, "configureWidget: "+appWidgetInfo.configure);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            startActivityForResult(intent, REQUEST_CREATE_APPWIDGET);
        } else {
            Log.d(TAG, "configureWidget: =null");
            createWidget(data);
        }
    }

    /**
     * Creates the widget and adds to our view layout.
     */
    public void createWidget(Intent data) {
        Bundle extras = data.getExtras();
        int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
        AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId);

        AppWidgetHostView hostView = mAppWidgetHost.createView(this, appWidgetId, appWidgetInfo);
        hostView.setAppWidget(appWidgetId, appWidgetInfo);
        textview.setVisibility(View.GONE);
        mainlayout.addView(hostView);

        Log.i("MainActivity", "The widget size is: " + appWidgetInfo.minWidth + "*" + appWidgetInfo.minHeight);

        if (extras != null) {
            for (String key : extras.keySet()) {
                Log.e(TAG, key + " : " + (extras.get(key) != null ? extras.get(key) : "NULL"));
            }
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
        mAppWidgetHost.deleteAppWidgetId(hostView.getAppWidgetId());
        mainlayout.removeView(hostView);
    }

    /**
     * Handles the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("MainActivity", "Menu selected: " + item.getTitle() + " / " + item.getItemId() + " / " + R.id.addWidget);
        switch (item.getItemId()) {
            case R.id.addWidget:
                selectWidget();
//                pickappWidget();
                return true;
            case R.id.removeWidget:
                removeWidgetMenuSelected();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Handle the 'Remove Widget' menu.
     */
    public void removeWidgetMenuSelected() {
        int childCount = mainlayout.getChildCount();
        if (childCount > 1) {
            View view = mainlayout.getChildAt(childCount - 1);
            if (view instanceof AppWidgetHostView) {
                removeWidget((AppWidgetHostView) view);
                Toast.makeText(this, R.string.widget_removed_popup, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(this, R.string.no_widgets_popup, Toast.LENGTH_SHORT).show();
    }

    /**
     * Creates the menu with options to add and remove widgets.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.widget_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        mAppWidgetHost.stopListening();
        mAppWidgetHost=null;
        removeWidget(hostView);
        super.onDestroy();
    }



    public boolean createBoolWidget(View view, String packageName, String className) {
        // Get the list of installed widgets
        AppWidgetProviderInfo newAppWidgetProviderInfo = null;
        List<AppWidgetProviderInfo> appWidgetInfos;
        appWidgetInfos = mAppWidgetManager.getInstalledProviders();
        boolean widgetIsFound = false;
        for(int j = 0; j < appWidgetInfos.size(); j++)
        {
            if (appWidgetInfos.get(j).provider.getPackageName().equals(packageName) && appWidgetInfos.get(j).provider.getClassName().equals(className))
            {
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
            hostView.setAppWidget(appWidgetId, newAppWidgetProviderInfo);

            customWidget.addView(hostView);

            // And bind widget IDs to make them actually work
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                boolean allowed = mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId, newAppWidgetProviderInfo.provider);

                if (!allowed) {
                    // Request permission - https://stackoverflow.com/a/44351320/1816603
                    Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, newAppWidgetProviderInfo.provider);

                    startActivityForResult(intent, REQUEST_BIND_WIDGET);
                }
            }

            return true;
        }
    }

}


