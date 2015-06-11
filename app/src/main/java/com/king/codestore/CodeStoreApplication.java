package com.king.codestore;

import android.app.Application;
import android.view.LayoutInflater;

/**
 * Created by houshijie on 15/6/11.
 */
public class CodeStoreApplication extends Application {
    private LayoutInflater mLayoutInflater ;
    private static CodeStoreApplication instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static final CodeStoreApplication getInstance(){
        return instance;
    }

    public LayoutInflater getLayoutInflater(){
        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        return mLayoutInflater;
    }
}
