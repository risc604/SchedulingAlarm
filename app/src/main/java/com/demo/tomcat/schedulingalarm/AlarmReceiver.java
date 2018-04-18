package com.demo.tomcat.schedulingalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.Calendar;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReceiver extends BroadcastReceiver
{
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    private AlarmManager    alarmMgr;
    private PendingIntent   alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.w(TAG, "onReceive(), ");
        Intent service = new Intent(context, SchedulingService.class);
        startWakefulService(context, service);
        throw new UnsupportedOperationException("Not yet implemented");
    }


    //----------------------- User function --------------------//
    public void setAlarm(Context context)
    {
        Log.w(TAG, " setAlarm(), ");
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                1000 * 60 * 3, alarmIntent);
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager  pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }

    public void cancelAlarm(Context context)
    {
        Log.w(TAG, " cancelAlarm(), ");

        if (alarmMgr != null)
        {
            alarmMgr.cancel(alarmIntent);
        }
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

}
