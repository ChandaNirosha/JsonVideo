package com.example.sys9.recyclerviewclicklistener;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLogTags;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videoURI;
    List<GS> gsList;
    Context context;
    TextView description;
    String desc;
    TextView title;
    String titl;

    //String desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        videoURI = getIntent().getStringExtra("video");
         description = (TextView) findViewById(R.id.text);
         desc = getIntent().getStringExtra("description");
        title = (TextView) findViewById(R.id.text2);
        titl = getIntent().getStringExtra("title");
        exoPlayerView =findViewById(R.id.exo_player_view);
        ArrayList<GS> gsList = new ArrayList<GS>();
        gsList = (ArrayList<GS>) getIntent().getSerializableExtra("gslist");
        Log.i("title and desc", desc + " ## " + titl+" $$ "+videoURI);
        Log.i("list ", gsList.toString());
        title.setText(titl);
        description.setText(desc);
        //  desc=getIntent().getStringExtra("des");
        try {


            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);


//

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoURI), dataSourceFactory, extractorsFactory, null, null);

            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        }catch (Exception e){
            Log.e("MainAcvtivity"," exoplayer error "+ e.toString());
        }
        ListView listView=findViewById(R.id.list_item);
        Adapter  adapter=new Adapter(getApplicationContext(),gsList);
        listView.setAdapter( adapter);
    }
}
