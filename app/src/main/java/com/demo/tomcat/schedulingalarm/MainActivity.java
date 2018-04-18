package com.demo.tomcat.schedulingalarm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    AlarmReceiver alarm = new AlarmReceiver();

    TextView    tvMessage;
    Button      btnOnOff;

    boolean     btnStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logFileCreated();
        Log.w(TAG, "onCreate(), ");

        initView();
        initControl();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "onRequestPermissionsResult()...");
        int grantTotal = 0;
        for (int i = 0; i < grantResults.length; i++) {
            grantTotal += grantResults[i];
        }
        Log.w(TAG, "grantTotal: " + grantTotal);

    }

    public void onClickedOnOff(View view)
    {
        Log.w(TAG, "onClickedOnOff(), ");
        if(btnStatus)
        {
            btnOnOff.setText("STart Alarm");
            btnStatus = false;
            alarm.cancelAlarm(this);
        }
        else
        {
            btnOnOff.setText("Stop Alarm");
            btnStatus = true;
            alarm.setAlarm(this);

        }

    }



    //---------------------- User function -------------------------//
    private void initView()
    {
        Log.w(TAG, " initView(), ");
        tvMessage = (TextView) findViewById(R.id.textmessage);
        btnOnOff = (Button) findViewById(R.id.onoffswitch);
        btnOnOff.setText("Start Alarm");
    }

    private void initControl()
    {
        Log.w(TAG, " initControl(), ");
        if (Build.VERSION.SDK_INT > 22)
            marshmallowPermission();
    }

    private boolean marshmallowPermission()
    {
        Log.i(TAG, " marshmallowPermission() ...");

        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int sysPermissionState = PackageManager.PERMISSION_GRANTED;

        if (storagePermission != sysPermissionState)
        {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{   Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },  1);
                ////Toast.makeText(this, "Please give App those permission To Run ...", Toast.LENGTH_LONG).show();
                Log.d(TAG, " Error !! PERMISSION_DENIED ");
                return false;
            }
            else
                return true;
        }
        else
        {
            Log.d(TAG, " PERMISSION_GRANTED ");
            return true;
        }
    }



    //private final static String mPID = String.valueOf(android.os.Process.myPid());
    final static String mPID = String.valueOf(android.os.Process.myPid());
    //final static String cmds01 = "logcat *:v *:w *:e *:d *:i | grep \"(" + mPID + ")\" -f ";
    final static String cmds01 = "logcat *:v | grep \"(" + mPID + ")\" -f ";
    public void logFileCreated()
    {
        try
        {
            //final String logFilePath = "/storage/emulated/0/Download/"+"Log_mt24.txt";
            final String logFilePath =  Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/Download/SchedulingAlarm.txt";
            //final String cmds01 = "logcat *:v | grep \"(" + mPID + ")\" -f ";

            boolean state = false;
            File f = new File(logFilePath);
            if (f.exists() && !f.isDirectory()) {
                if (!f.delete()) {
                    Log.w(TAG, "FAIL !! file delete NOT ok.");
                }
                else
                {
                    state = f.createNewFile();
                }
            }

            java.lang.Process process = Runtime.getRuntime().exec(cmds01 + logFilePath);
            Log.w(TAG, "logFileCreated(), process: " + process.toString() +
                    ", path: " + logFilePath + ", f.exists: " + state);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }



}

