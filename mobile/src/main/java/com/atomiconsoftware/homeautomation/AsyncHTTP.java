package com.atomiconsoftware.homeautomation;

import android.os.AsyncTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.loopj.android.http.*;


/**
 * Created by Newyork167 on 10/18/2015.
 */
public class AsyncHTTP extends AsyncTask<String, Integer, String> {

    private Context context;

    public AsyncHTTP(Context context){
        this.context = context;
    }



    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}

