package com.atomiconsoftware.homeautomation;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.HttpGet;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.mime.Header;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import cz.msebera.android.httpclient.impl.auth.BasicScheme;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

public class HomeAutomationGPIOClient {
    private static final String BASE_URL = "";
    private static final String TAG = "HA-HTTP";
    private static final String username = "";
    private static final String password = "";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public Context context;

    public HomeAutomationGPIOClient(Context context){
        this.context = context;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.post(getAbsoluteUrl(url), params, responseHandler);

        try {
            HttpHost targetHost = new HttpHost(BASE_URL, 8001, "http");

            DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
                // Store the user login
                httpclient.getCredentialsProvider().setCredentials(
                        new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                        new UsernamePasswordCredentials(username, password));

                HttpPost httppost = new HttpPost(BASE_URL);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("outletId", "1"));
                nameValuePairs.add(new BasicNameValuePair("outletStatus", "on"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity));
            } finally {
                httpclient.getConnectionManager().shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void postOutlet(Context context, RequestParams params, AsyncHttpResponseHandler responseHandler) {


        client.setBasicAuth(username, password, new AuthScope("thebatcomputer.net:8001", 80, AuthScope.ANY_REALM));
        client.addHeader(
                "Authorization",
                "Basic " + Base64.encodeToString(
                        (username+":"+password).getBytes(), Base64.NO_WRAP)
        );

        Log.d(TAG, "postOutlet: " + getAbsoluteUrl("") + "\n\tParams: " + params.toString());
        Log.d(TAG, "postOutlet: " + client.toString());
        client.post(getAbsoluteUrl(""), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
