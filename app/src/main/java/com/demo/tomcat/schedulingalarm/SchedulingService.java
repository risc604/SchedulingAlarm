package com.demo.tomcat.schedulingalarm;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class SchedulingService extends Service
{
    private static final String TAG = SchedulingService.class.getSimpleName();

    public static final String TAG1 = "Schedualing Demo";
    public static final int NOTIFICATION_ID = 10;
    public static final String SEARCH_STRING = "doodle";
    public static final String URL = "https://www.google.com";
    private NotificationManager notificationManager;
    NotificationCompat.Builder  builder;

    public SchedulingService()
    {
        Log.w(TAG, "SchedulingService(), constructor");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        HandlerThread thread = new HandlerThread("Service[" + mName + "]");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}
