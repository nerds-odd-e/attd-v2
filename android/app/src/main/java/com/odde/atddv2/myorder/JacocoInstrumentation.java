package com.odde.atddv2.myorder;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JacocoInstrumentation  extends Instrumentation implements InstrumentActivityListener {
    public static String TAG = "JacocoInstrumentation:";
    private final Bundle mResults = new Bundle();
    private Intent mIntent;

    public JacocoInstrumentation() {
    }
    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        new File(getContext().getFilesDir().getPath() + "/coverageData").mkdir();
        mIntent = new Intent(getTargetContext(), MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start();
    }
    @Override
    public void onStart() {
        super.onStart();
        Looper.prepare();
        // Register broadcast receiver and start InstrumentActivity
        MainActivity activity = (MainActivity) startActivitySync(mIntent);
        EndEmmaBroadcast broadcast = new EndEmmaBroadcast();
        broadcast.setInstrumentActivityListener(this);
        activity.registerReceiver(broadcast, new IntentFilter("com.odde.atddv2.myorder.END_EMMA"));
    }
    private void generateCoverageReport() {
        OutputStream out = null;
        try {
            String coverageFilePath = getContext().getFilesDir().getPath() + "/coverageData/coverage_" + System.currentTimeMillis() + ".ec";
            out = new FileOutputStream(coverageFilePath, false);
            Object agent = Class.forName("org.jacoco.agent.rt.RT")
                    .getMethod("getAgent")
                    .invoke(null);
            out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class)
                    .invoke(agent, false));
        } catch (Exception e) {
            Log.d(TAG, e.toString(), e);
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

    @Override
    public void onActivityEnd() {
        Log.d(TAG, "onActivityFinished()");
        generateCoverageReport();
        finish(Activity.RESULT_OK, mResults);
    }
}
