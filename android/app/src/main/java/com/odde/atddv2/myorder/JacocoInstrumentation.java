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
    private static String DEFAULT_COVERAGE_FILE_PATH = null;
    private final Bundle mResults = new Bundle();
    private Intent mIntent;
    private static final boolean LOGD = true;
    private boolean mCoverage = true;
    private static String mCoverageFilePath;

    public JacocoInstrumentation() {
    }
    @Override
    public void onCreate(Bundle arguments) {
        Log.d(TAG, "onCreate(" + arguments + ")");
        super.onCreate(arguments);
        // bad notation, better use NAME+TimeSeed because you might generate more than 1 corage file
        DEFAULT_COVERAGE_FILE_PATH = getContext().getFilesDir().getPath().toString() + "/coverage.ec";
        Log.d(TAG, "DEFAULT_COVERAGE_FILE_PATH:" + DEFAULT_COVERAGE_FILE_PATH);
        File file = new File(DEFAULT_COVERAGE_FILE_PATH);
        if(!file.exists()){
            try{
                Log.d(TAG,"File not exist, create new file");
                file.createNewFile();
                Log.d(TAG,"File created");
            }catch (IOException e){
                Log.d(TAG,"File Exception ："+e);
                e.printStackTrace();}
        }
        Log.d(TAG, "arguments:" + arguments);
        if(arguments != null) {
            mCoverageFilePath = arguments.getString("coverageFile");
        }
        Log.d(TAG, "mCoverageFilePath:" + mCoverageFilePath);
        mIntent = new Intent(getTargetContext(), MainActivity.class);
        Log.d(TAG, "mIntent:" + mIntent);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d(TAG, "before start");
        start();
        Log.d(TAG, "after start");
    }
    @Override
    public void onStart() {
        Log.d(TAG, "onStart()");
        super.onStart();
        Log.d(TAG, "before Looper.prepare()");
        Looper.prepare();
        Log.d(TAG, "before startActivitySync()");
        // Register broadcast receiver and start InstrumentActivity
        MainActivity activity = (MainActivity) startActivitySync(mIntent);
        Log.d(TAG, "after startActivitySync()");
        EndEmmaBroadcast broadcast = new EndEmmaBroadcast();
        Log.d(TAG, "after new EndEmmaBroadcast(): " + broadcast);
        activity.setListener(this);
        Log.d(TAG, "after activity.setListener(this)");
        broadcast.setInstrumentActivityListener(this);
        Log.d(TAG, "after broadcast.setInstrumentActivityListener(this)");
        activity.registerReceiver(broadcast, new IntentFilter("com.odde.atddv2.myorder.END_EMMA"));
        Log.d(TAG, "after activity.registerReceiver(broadcast, new IntentFilter(\"com.odde.atddv2.END_EMMA\"))");
    }
    private String getCoverageFilePath() {
        if (mCoverageFilePath == null) {
            return DEFAULT_COVERAGE_FILE_PATH;
        } else {
            return mCoverageFilePath;
        }
    }
    private void generateCoverageReport() {
        Log.d(TAG, "generateCoverageReport():" + getCoverageFilePath());
        OutputStream out = null;
        try {
            out = new FileOutputStream(getCoverageFilePath(), false);
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
        if (mCoverage) {
            generateCoverageReport();
        }
        finish(Activity.RESULT_OK, mResults);
    }
}
