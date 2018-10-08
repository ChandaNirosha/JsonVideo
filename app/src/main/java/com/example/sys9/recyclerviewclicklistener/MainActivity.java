package com.example.sys9.recyclerviewclicklistener;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    private ListView listView;
    private List<GS> gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.recyclerview);
        String url = "https://interview-e18de.firebaseio.com/media.json?print=pretty";
        new ResponseAsync().execute(url);


    }

    private class ResponseAsync extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("get response Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();

        }


        @Override
        protected String doInBackground(String... url) {
            String respURL = url[0];

            try {
                URL ur = new URL(respURL);
                InputStream is = ur.openConnection().getInputStream();

                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }


                return buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override

        protected void onPostExecute(String s) {
            List<GS> gsList = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(s);

                for (int i = 0; i < array.length(); i++) {
                    JSONObject list_obj = array.getJSONObject(i);
                    GS item = new GS();
                    item.setDescription(list_obj.getString("description"));
                    item.setid(list_obj.getString("id"));
                    item.setImageUrl(list_obj.getString("thumb"));
                    item.setVidUrl(list_obj.getString("url"));
                    item.setTitle(list_obj.getString("title"));
                    gsList.add(item);
                }


                //JSONObject object= array.getJSONObject();


            } catch (JSONException e) {
                e.printStackTrace();

            }
            Adapter adapter = new Adapter(getApplicationContext(), gsList);
            listView.setAdapter(adapter);
            mProgressDialog.dismiss();
        }

    }}




