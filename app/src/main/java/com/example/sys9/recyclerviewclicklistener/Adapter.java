package com.example.sys9.recyclerviewclicklistener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Adapter extends BaseAdapter {
    Context context;
    List<GS> gsList;

    public Adapter(Context context, List<GS> gsList) {
        this.context = context;
        this.gsList = gsList;
    }

    @Override
    public int getCount() {
        return gsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final  int i, View view, ViewGroup viewGroup) {
        View cview = LayoutInflater.from(context).inflate(R.layout.text, viewGroup, false);
        TextView name = cview.findViewById(R.id.descri);
        ImageView view1 = cview.findViewById(R.id.image);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("video", gsList.get(i).getVidUrl());
                intent.putExtra("description",gsList.get(i).getDescription());
                intent.putExtra("title",gsList.get(i).getTitle());
                intent.putExtra("gslist",(Serializable)gsList);
                 gsList.remove(i);
                context.startActivity(intent);
            }
        });
        name.setText(gsList.get(i).getDescription());
        TextView name2 = cview.findViewById(R.id.title);
        name2.setText(gsList.get(i).getTitle());


        Picasso.get().load(gsList.get(i).getImageUrl()).into(view1);
        view = cview;


        return view;

    }
}






























