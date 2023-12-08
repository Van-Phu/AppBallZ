package com.example.ballz.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ballz.Model.HistoryFight;
import com.example.ballz.Model.Matchup;
import com.example.ballz.R;

import java.util.ArrayList;

public class CustomAdapterHistoryFight extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<HistoryFight> lstHistory = new ArrayList<>();

    public CustomAdapterHistoryFight(@NonNull Context context, int resource, ArrayList<HistoryFight> lstHistory) {
        super(context, resource, lstHistory);
        this.context = context;
        this.layoutItem = resource;
        this.lstHistory = lstHistory;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HistoryFight h = (HistoryFight) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_history_fight, parent, false);
        }
        TextView tvNameHome = (TextView) convertView.findViewById(R.id.tvNameHome);
        TextView tvNameAway = (TextView) convertView.findViewById(R.id.tvNameAway);
        TextView tvScored = (TextView) convertView.findViewById(R.id.tvScored);

        int scoredHome = Integer.parseInt(h.getScoreHome());
        int scoredAway = Integer.parseInt(h.getScoreAway());

        tvNameHome.setText(h.getNameHome());
        tvNameAway.setText(h.getNameAway());

        tvScored.setText(scoredHome + "-" + scoredAway);
        if(scoredHome > scoredAway){
            tvScored.setBackgroundColor(Color.GREEN);
        } else if (scoredHome == scoredAway) {
            tvScored.setBackgroundColor(Color.GRAY);
        }else if(scoredHome < scoredAway){
            tvScored.setBackgroundColor(Color.RED);
        }


        return convertView;
    }
}
