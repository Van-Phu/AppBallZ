package com.example.ballz.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ballz.Model.Matchup;
import com.example.ballz.Model.NewMatch;
import com.example.ballz.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapterMatchup extends ArrayAdapter {

    Context context;
    int layoutItem;
    ArrayList<Matchup> lstMatchup = new ArrayList<>();

    public CustomAdapterMatchup(@NonNull Context context, int resource, ArrayList<Matchup> lstMatchup) {
        super(context, resource, lstMatchup);
        this.context = context;
        this.layoutItem = resource;
        this.lstMatchup = lstMatchup;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Matchup matchup = (Matchup) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_headtohead, parent, false);
        }
        ImageView logoHome = (ImageView) convertView.findViewById(R.id.logoHome);
        ImageView logoAway = (ImageView) convertView.findViewById(R.id.logoAway);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);
        TextView tvTour = (TextView) convertView.findViewById(R.id.tvTournament);

        tvTime.setText(matchup.getDate());
        tvScore.setText(matchup.getScore());
        tvTour.setText(matchup.getTour());
        checkIcon(matchup.getLogoHome(), logoHome);
        checkIcon(matchup.getLogoAway(), logoAway);
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
