package com.example.hmnotes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class URLJsonTask extends AsyncTask<String, String, String> {

    private ProgressDialog progressDialog;

    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(HMnoteActivity.mainContext);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected String doInBackground(String... params) {


        URLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            System.out.println("url ===== " + url);
            connection = url.openConnection();
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }

            System.out.println("STRINGBUFFER ===== " + stringBuffer.toString());
            return stringBuffer.toString();


//            InputStream stream = connection.getInputStream();
//
//            reader = new BufferedReader(new InputStreamReader(stream));
//
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line+"\n");
//                System.out.println("Response: > " + line);
//                //Log.d("Response: ", "> " + line);
//
//            }
//
//            return buffer.toString();


        } catch (MalformedURLException e) {
            System.out.println("MALFORMED EXCEPTION =====");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOEXCEPTION ====");
            e.printStackTrace();
        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
            System.out.println("CONNECTION IS CLOSED =====");
            try {
                if (reader != null) {
                    reader.close();
                }
                System.out.println("READER IS CLOSED =====");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("READER ERROR ======");
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        System.out.println("RESULT ====== " + result);
        HMnoteActivity.weatherBar.setText(result);
    }
}
