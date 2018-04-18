package com.demo.tomcat.schedulingalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver
{
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.w(TAG, "onReceive(), ");
        throw new UnsupportedOperationException("Not yet implemented");
    }


    //----------------------- User function --------------------//
    public void setAlarm(Context context)
    {
        Log.w(TAG, " setAlarm(), ");

    }

    public void cancelAlarm(Context context)
    {
        Log.w(TAG, " cancelAlarm(), ");
    }

}
