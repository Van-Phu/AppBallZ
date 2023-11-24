package com.example.ballz.Model;

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

import com.example.ballz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterNews extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<News> lstNews = new ArrayList<>();

    public CustomAdapterNews(@NonNull Context context, int resource, ArrayList<News> lstNews) {
        super(context, resource, lstNews);
        this.context = context;
        this.layoutItem = resource;
        this.lstNews = lstNews;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news = (News) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_news_main, parent, false);
        }
        ImageView imageNews = convertView.findViewById(R.id.imageAvatar);
        Picasso.get().load(news.getImage()).resize(100, 100).into(imageNews);
        TextView title = convertView.findViewById(R.id.tvTitle);
        title.setText(news.getTitle());
        Log.d("ImageUrl", news.getUrlNews());
        return convertView;
    }

}
