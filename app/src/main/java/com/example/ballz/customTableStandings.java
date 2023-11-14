package com.example.ballz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class customTableStandings extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<ClubStanding> lsClubStanding = new ArrayList<>();

    public customTableStandings(@NonNull Context context, int resource, ArrayList<ClubStanding> lsClubStanding) {
        super(context, resource, lsClubStanding);

        this.context = context;
        this.layoutItem = resource;
        this.lsClubStanding = lsClubStanding;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClubStanding clubStanding = lsClubStanding.get(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }
        ImageView imgClub = (ImageView) convertView.findViewById(R.id.imgClub);

        TextView tvNameClub = (TextView) convertView.findViewById(R.id.tvNameClub);
        TextView tvWinNum = (TextView) convertView.findViewById(R.id.tvWinNum);

        TextView tvDrawNum = (TextView) convertView.findViewById(R.id.tvDrawNum);
        TextView tvLoseNum = (TextView) convertView.findViewById(R.id.tvLoseNum);
        TextView tvPoint = (TextView) convertView.findViewById(R.id.tvPoint);


        Picasso.with(context).load(clubStanding.getImg()).resize(100,100).into(imgClub);
        tvNameClub.setText(clubStanding.getNameClub());
        tvWinNum.setText(clubStanding.getWinNumb());
        tvDrawNum.setText(clubStanding.getDrawNumb());
        tvLoseNum.setText(clubStanding.getLoseNumb());
        tvPoint.setText(clubStanding.getPoint());

        return convertView;

    }
}
