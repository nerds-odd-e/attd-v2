package com.odde.atddv2.coverage;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// adb shell am broadcast -a com.example.pkg.END_EMMA
public class EndEmmaBroadcast extends BroadcastReceiver {
    InstrumentActivityListener activityListener;

    public void setInstrumentActivityListener(InstrumentActivityListener listener){
        this.activityListener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("jacoco", "On Receive");
        if(this.activityListener!=null){
            activityListener.onActivityEnd();
        }
        // once coverage is dumped, the processes is ended.
//        Process.killProcess(Process.myPid());
    }

}
