package com.hisl.glance.free.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.hisl.glance.free.activity.theme.Theme1;
import com.hisl.glance.free.activity.theme.Theme2;

@SuppressWarnings("deprecation")
public class ScreenOffReceiver extends BroadcastReceiver {

    private TelephonyManager telephonyManager = null;
    private boolean isPhoneIdle = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(telephonyManager == null){
            telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        }

        if(isPhoneIdle) {

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Class ac = null;
                SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
                switch(pref.getInt("theme", 0)){
                    case 0:
                        ac = Theme1.class;
                        break;
                    case 1:
                        ac = Theme2.class;
                        break;
                }
                Intent i = new Intent(context, ac);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }

    private PhoneStateListener phoneListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber){
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE :
                    isPhoneIdle = true;
                    break;
                case TelephonyManager.CALL_STATE_RINGING :
                    isPhoneIdle = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK :
                    isPhoneIdle = false;
                    break;
            }
        }
    };
}