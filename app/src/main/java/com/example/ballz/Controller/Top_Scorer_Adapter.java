package com.example.ballz.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ballz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Top_Scorer_Adapter extends BaseAdapter {
    Context context;
    ArrayList<String> playerNameList;
    ArrayList<String> playerPositionList;
    ArrayList<String> topGoalList;
    ArrayList<String> playerPhotoList;
    ArrayList<String> clubImgList;
    LayoutInflater inflater;

    public Top_Scorer_Adapter(Context ctx, ArrayList<String> playerNameList, ArrayList<String> playerPositionList, ArrayList<String> topGoalList, ArrayList<String> clubImgList, ArrayList<String> playerPhotoList) {
        this.context = ctx;
        this.playerNameList = playerNameList;
        this.playerPositionList = playerPositionList;
        this.topGoalList = topGoalList;
        this.clubImgList = clubImgList;
        this.playerPhotoList = playerPhotoList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return playerNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_top_scorer_card, null);

        TextView txtFootballPlayerName = convertView.findViewById(R.id.txtFootballPlayerName);
        TextView txtFootballPlayerPosition = convertView.findViewById(R.id.txtFootballPlayerPosition);
        TextView txtFootballPlayerNum = convertView.findViewById(R.id.txtFootballPlayerNum);
        ImageView imgFootballClubLogo = convertView.findViewById(R.id.imgFootballClubLogo);
        ImageView imgFootballPlayer = convertView.findViewById(R.id.imgFootballPlayer);


        txtFootballPlayerName.setText(playerNameList.get(position));
        txtFootballPlayerPosition.setText(playerPositionList.get(position));
        txtFootballPlayerNum.setText(topGoalList.get(position));

        Picasso.get().load(clubImgList.get(position)).into(imgFootballClubLogo);
        Picasso.get().load(playerPhotoList.get(position)).into(imgFootballPlayer);

        return convertView;
    }
}
