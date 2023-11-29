package com.odde.atddv2.myorder;

import android.util.Log;


public class InstrumentActivity extends MainActivity {
    public static String TAG = "IntrumentedActivity";
    private InstrumentActivityListener listener;

    public void setInstrumentActivityListener(InstrumentActivityListener listener) {
        this.listener = listener;
    }

    // Generate output report when the activity is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        super.finish();
        if (listener != null) {
            listener.onActivityEnd();
        }
    }
}
