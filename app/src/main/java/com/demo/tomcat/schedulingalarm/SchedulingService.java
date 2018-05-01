package com.demo.tomcat.schedulingalarm;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
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
    private Messenger messenger;

    public SchedulingService()
    {
        Log.w(TAG, "SchedulingService(), constructor");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.w(TAG, "onStartCommand(), ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate()
    {
        Log.w(TAG, "onStartCommand(), ");
        //super.onCreate();

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                Log.w(TAG, " handleMessage(), msg: " + msg.what);
                //super.handleMessage(msg);
                sendMessageToActivity(msg.replyTo);
            }
        };
        messenger = new Messenger(handler);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.w(TAG, "onBind(), ");
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.w(TAG, "onUnbind(), ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        Log.w(TAG, "onDestroy(), ");
        super.onDestroy();
    }


    //----------------------- user function ---------------------------//
    private void sendMessageToActivity(Messenger mMessenger)
    {
        Log.w(TAG, " sendMessageToActivity(), ");
        Message msg = Message.obtain();
        msg.what = 0x09;
        msg.obj = "hello, I'm from Service !";

        try
        {
            mMessenger.send(msg);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
