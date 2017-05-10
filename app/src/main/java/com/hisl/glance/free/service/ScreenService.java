package com.hisl.glance.free.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;

import com.hisl.glance.free.receiver.PackageReceiver;
import com.hisl.glance.free.receiver.RestartReceiver;
import com.hisl.glance.free.receiver.ScreenOffReceiver;

/**
 * Created by mga11 on 2016-08-28.
 */
public class ScreenService extends Service {

    private ScreenOffReceiver mReceiver = null;
    private PackageReceiver pReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mReceiver = new ScreenOffReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);

        pReceiver = new PackageReceiver();
        IntentFilter pFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        pFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        pFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        pFilter.addDataScheme("package");
        registerReceiver(pReceiver, pFilter);
    }

    public void registerRestartAlarm(boolean isOn){
        Intent intent = new Intent(ScreenService.this, RestartReceiver.class);
        intent.setAction(RestartReceiver.ACTION_RESTART_SERVICE);
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(isOn){
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000, 10000, sender);
        }else{
            am.cancel(sender);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        if(pref.getBoolean("onoff", false)) {
            if (intent != null) {
                if (intent.getAction() == null) {
                    if (mReceiver == null) {
                        mReceiver = new ScreenOffReceiver();
                        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                        registerReceiver(mReceiver, filter);
                    }
                }
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if(mReceiver != null){
            unregisterReceiver(mReceiver);
        }
        if(pReceiver != null)
            unregisterReceiver(pReceiver);
    }
}