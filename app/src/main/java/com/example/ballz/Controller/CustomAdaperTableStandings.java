package com.example.ballz.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ballz.Model.ClubStanding;
import com.example.ballz.R;
import com.example.ballz.View.FragmentSquad;

import java.util.ArrayList;

public class CustomAdaperTableStandings extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<ClubStanding> lsClubStanding = new ArrayList<>();

    public CustomAdaperTableStandings(@NonNull Context context, int resource, ArrayList<ClubStanding> lsClubStanding) {
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

        checkIcon(clubStanding.getNameClub(),imgClub);
        tvNameClub.setText(clubStanding.getNameClub());
        tvWinNum.setText(clubStanding.getWinNumb());
        tvDrawNum.setText(clubStanding.getDrawNumb());
        tvLoseNum.setText(clubStanding.getLoseNumb());
        tvPoint.setText(clubStanding.getPoint());


        return convertView;

    }
    private void checkIcon(String icon, ImageView view) {
        switch (icon) {
            case "Manchester City":
                view.setImageResource(R.drawable.manchester_city);
                break;
            case "Liverpool":
                view.setImageResource(R.drawable.liverpool);
                break;
            case "Arsenal":
                view.setImageResource(R.drawable.arsenal);
                break;
            case "Tottenham Hotspur":
                view.setImageResource(R.drawable.tottenham);
                break;
            case "Aston Villa":
                view.setImageResource(R.drawable.astonvilla);
                break;
            case "Manchester United":
                view.setImageResource(R.drawable.manchester_utd);
                break;
            case "Newcastle":
                view.setImageResource(R.drawable.newcastle);
                break;
            case "Brighton":
                view.setImageResource(R.drawable.brighton);
                break;
            case "West Ham":
                view.setImageResource(R.drawable.west_ham);
                break;
            case "Chelsea":
                view.setImageResource(R.drawable.chelsea);
                break;
            case "Brentford":
                view.setImageResource(R.drawable.brentford);
                break;
            case "Wolverhampton Wanderers":
                view.setImageResource(R.drawable.wolves);
                break;
            case "Crystal Palace":
                view.setImageResource(R.drawable.crystal_palace);
                break;
            case "Everton":
                view.setImageResource(R.drawable.everton);
                break;
            case "Forest":
                view.setImageResource(R.drawable.nottingham);
                break;
            case "Fulham":
                view.setImageResource(R.drawable.fulham);
                break;
            case "Bournemouth":
                view.setImageResource(R.drawable.bournemouth);
                break;
            case "Luton Town":
                view.setImageResource(R.drawable.luton);
                break;
            case "Sheffield United":
                view.setImageResource(R.drawable.sheffield_utd);
                break;
            case "Burnley":
                view.setImageResource(R.drawable.burnley);
                break;
            default:
                break;
        }
    }
}
