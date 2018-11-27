package com.example.bryan.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Network {

    public static boolean checkNetwork(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetwork() !=null;
    }

    public static String excuteGet(String accessURL, String urlParam){
        URL url;
        HttpURLConnection connect = null;
        try{
            url = new URL(accessURL);
            connect = (HttpURLConnection)url.openConnection();
            connect.setRequestProperty("content-type","application/json; charset=utf-8");
            connect.setRequestProperty("Content-Language", "en-US");
            connect.setUseCaches(false);
            connect.setDoInput(true);
            connect.setDoOutput(false);
            InputStream inputStream;
            int statusConnection = connect.getResponseCode();

            if(statusConnection != HttpURLConnection.HTTP_OK)
                inputStream = connect.getErrorStream();
            else
                inputStream = connect.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = bufferedReader.readLine()) !=null){
                response.append(line);
                response.append('\r');

            }
            bufferedReader.close();
            return response.toString();

        }catch (Exception e){
            return null;
        } finally {
            if(connect != null){
                connect.disconnect();
            }
        }
    }

}
