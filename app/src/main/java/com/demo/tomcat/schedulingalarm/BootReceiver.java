package com.demo.tomcat.schedulingalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver
{
    private static final String TAG = BootReceiver.class.getSimpleName();
    AlarmReceiver   alarm = new AlarmReceiver();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.w(TAG, "onReceive(), ");
        // TODO: This method is called when the BroadcastReceiver is receiving
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            alarm.setAlarm(context);
        }
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
