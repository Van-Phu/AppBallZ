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

public class CustomAdapterNewMatchMain extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<NewMatch> lstNewMatch = new ArrayList<>();

    public CustomAdapterNewMatchMain(@NonNull Context context, int resource, ArrayList<NewMatch> lstNewMatch) {
        super(context, resource, lstNewMatch);
        this.context = context;
        this.layoutItem = resource;
        this.lstNewMatch = lstNewMatch;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewMatch newMatch = (NewMatch) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.new_match_table_layout_custom, parent, false);
        }
        ImageView logoHome = (ImageView) convertView.findViewById(R.id.logoHome);
        ImageView logoAway = (ImageView) convertView.findViewById(R.id.logoAway);
        TextView nameHome = convertView.findViewById(R.id.tvHome);
        TextView nameAway = convertView.findViewById(R.id.tvAway);
        TextView time = convertView.findViewById(R.id.tvTime);

        nameHome.setText(newMatch.getNameHome());
        nameAway.setText(newMatch.getNameAway());
        time.setText(newMatch.getEventStart());
        checkIcon(newMatch.getLogoHome(), logoHome);
        checkIcon(newMatch.getLogoAway(), logoAway);
        return convertView;
    }

    private void checkIcon(String icon, ImageView view) {
        switch (icon) {
            case "Man City ":
                view.setImageResource(R.drawable.manchester_city);
                break;
            case "Chelsea":
                view.setImageResource(R.drawable.chelsea);
                break;
            case "Liverpool":
                view.setImageResource(R.drawable.liverpool);
                break;
            case "Brentford":
                view.setImageResource(R.drawable.brentford);
                break;
            case "West Ham":
                view.setImageResource(R.drawable.west_ham);
                break;
            case "Forest":
                view.setImageResource(R.drawable.nottingham);
                break;
            case "Aston Villa":
                view.setImageResource(R.drawable.astonvilla);
                break;
            case "Fulham":
                view.setImageResource(R.drawable.fulham);
                break;
            case "Brighton":
                view.setImageResource(R.drawable.brighton);
                break;
            case "Sheffield":
                view.setImageResource(R.drawable.sheffield_utd);
                break;
            case "Bournemouth":
                view.setImageResource(R.drawable.bournemouth);
                break;
            case "Newcastle":
                view.setImageResource(R.drawable.newcastle);
                break;
            case "Man United":
                view.setImageResource(R.drawable.manchester_utd);
                break;
            case "Luton Town":
                view.setImageResource(R.drawable.luton);
                break;
            case "Palace":
                view.setImageResource(R.drawable.crystal_palace);
                break;
            case "Everton":
                view.setImageResource(R.drawable.everton);
                break;
            case "Arsenal":
                view.setImageResource(R.drawable.arsenal);
                break;
            case "Burnley":
                view.setImageResource(R.drawable.burnley);
                break;
            case "Wolves":
                view.setImageResource(R.drawable.wolves);
                break;
            case "Tottenham":
                view.setImageResource(R.drawable.tottenham);
                break;
            default:
                break;
        }
    }
}
