package com.example.ballz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterReplay extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Video> listVideo = new ArrayList<>();

    public CustomAdapterReplay(@NonNull Context context, int resource, ArrayList<Video> listVideo) {
        super(context, resource, listVideo);
        this.context = context;
        this.layoutItem = resource;
        this.listVideo = listVideo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Video video = (Video) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_video, parent, false);
        }
//        ImageView imageNews = convertView.findViewById(R.id.imgVideo);
//        Picasso.get().load(Video.getImage()).resize(100, 100).into(imageNews);
        TextView tvReplay = convertView.findViewById(R.id.tvReplay);
        tvReplay.setText(video.getVideoTitle());
        return convertView;
    }
}


