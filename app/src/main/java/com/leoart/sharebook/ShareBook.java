package com.leoart.sharebook;

import android.app.Application;

public class ShareBook extends Application{

    private static final String TAG = "ShareBook Application";

    private static ShareBook instance;

    public static ShareBook getInstance() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;

    }
}
