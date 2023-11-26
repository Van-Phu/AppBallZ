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

import com.example.ballz.R;

import java.util.ArrayList;

public class CustomAdapterAllGoalScores extends ArrayAdapter {
    Context context;
    int layoutItem;

    ArrayList<AllGoalScores> lsAllGoalScores = new ArrayList<>();

    public CustomAdapterAllGoalScores(@NonNull Context context, int resource, ArrayList<AllGoalScores> lsAllGoalScores) {
        super(context, resource, lsAllGoalScores);
        this.context = context;
        this.layoutItem = resource;
        this.lsAllGoalScores = lsAllGoalScores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AllGoalScores allGoalScores = lsAllGoalScores.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }


        TextView tvNumberOfNumAllG = (TextView) convertView.findViewById(R.id.tvNumberOfNumAllG);
        TextView tvNamePlayerAllG = (TextView) convertView.findViewById(R.id.tvNamePlayerAllG);
        TextView tvNameClubAllG = (TextView) convertView.findViewById(R.id.tvNameClubAllG);
        TextView tvGoalAllG = (TextView) convertView.findViewById(R.id.tvGoalAllG);
        ImageView imgNameClubAllG =  (ImageView) convertView.findViewById(R.id.imgNameClubAllG);

        tvNumberOfNumAllG.setText(String.valueOf(allGoalScores.getRank())+".");
        tvNamePlayerAllG.setText(allGoalScores.getPlayer_name());
        tvGoalAllG.setText(String.valueOf(allGoalScores.getGoal()));
        checkIDByPlayer(allGoalScores.getTeam_name(), imgNameClubAllG, tvNameClubAllG);
        return convertView;
    }

    private void checkIDByPlayer(int icon, ImageView view, TextView name) {
        switch (icon) {
            case 8456:
                view.setImageResource(R.drawable.manchester_city);
                name.setText("Man City");
                break;
            case 8455:
                view.setImageResource(R.drawable.chelsea);
                name.setText("Chelsea");
                break;
            case 8650:
                view.setImageResource(R.drawable.liverpool);
                name.setText("Liverpool");
                break;
            case 9937:
                view.setImageResource(R.drawable.brentford);
                name.setText("Brentford");
                break;
            case 8654:
                view.setImageResource(R.drawable.west_ham);
                name.setText("West Ham");
                break;
            case 10203:
                view.setImageResource(R.drawable.nottingham);
                name.setText("Forest");
                break;
            case 10252:
                view.setImageResource(R.drawable.astonvilla);
                name.setText("Aston Villa");
                break;
            case 9879:
                view.setImageResource(R.drawable.fulham);
                name.setText("Fulham");
                break;
            case 10204:
                view.setImageResource(R.drawable.brighton);
                name.setText("Brighton");
                break;
            case 8657:
                view.setImageResource(R.drawable.sheffield_utd);
                name.setText("Sheffield");
                break;
            case 8678:
                view.setImageResource(R.drawable.bournemouth);
                name.setText("Bournemouth");
                break;
            case 10261:
                view.setImageResource(R.drawable.newcastle);
                name.setText("Newcastle");
                break;
            case 10260:
                view.setImageResource(R.drawable.manchester_utd);
                name.setText("Man United");
                break;
            case 8346:
                view.setImageResource(R.drawable.luton);
                name.setText("Luton Town");
                break;
            case 9826:
                view.setImageResource(R.drawable.crystal_palace);
                name.setText("Cystal Palace");
                break;
            case 8668:
                view.setImageResource(R.drawable.everton);
                name.setText("Everton");
                break;
            case 9825:
                view.setImageResource(R.drawable.arsenal);
                name.setText("Arsenal");
                break;
            case 8191:
                view.setImageResource(R.drawable.burnley);
                name.setText("Burnley");
                break;
            case 8602:
                view.setImageResource(R.drawable.wolves);
                name.setText("Wolves");
                break;
            case 8586:
                view.setImageResource(R.drawable.tottenham);
                name.setText("Tottenham");
                break;
            default:
                break;
        }
    }
}
