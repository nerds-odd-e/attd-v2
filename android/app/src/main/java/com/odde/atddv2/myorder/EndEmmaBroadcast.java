package com.odde.atddv2.myorder;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// adb shell am broadcast -a com.example.pkg.END_EMMA
public class EndEmmaBroadcast extends BroadcastReceiver {
    InstrumentActivityListener activityListener;

    public void setInstrumentActivityListener(InstrumentActivityListener listener){
        this.activityListener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("jacoco", "onReceive(): " + this);
//        JacocoInstrumentation.generateCoverageReport();
//        generateCoverageReport();
        Log.d("jacoco", "listener:" + activityListener);
        if(this.activityListener!=null){
            Log.d("jacoco", "listener is not null");
            activityListener.onActivityEnd();
        }
        // once coverage is dumped, the processes is ended.
//        Process.killProcess(Process.myPid());
    }

    private String getCoverageFilePath() {
        return MyOrderApp.Companion.getAppContext().getFilesDir().getPath().toString() + "/coverage.ec";
    }
    private void generateCoverageReport() {
        Log.d("jacoco", "generateCoverageReport():" + getCoverageFilePath());
        OutputStream out = null;
        try {
            out = new FileOutputStream(getCoverageFilePath(), false);
            Object agent = Class.forName("org.jacoco.agent.rt.RT")
                    .getMethod("getAgent")
                    .invoke(null);
            out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class)
                    .invoke(agent, false));
        } catch (Exception e) {
            Log.d("jacoco", e.toString(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
