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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomTopScores extends ArrayAdapter {
    Context context;
    int layoutItem;

    ArrayList<TopScores> lsTopScores = new ArrayList<>();

    public CustomTopScores(@NonNull Context context, int resource, ArrayList<TopScores> lsTopScores) {
        super(context, resource, lsTopScores);
        this.context = context;
        this.layoutItem = resource;
        this.lsTopScores = lsTopScores;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TopScores topScores = lsTopScores.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }


        TextView tvNumberOfNum = (TextView) convertView.findViewById(R.id.tvNumberOfNum);
        TextView tvNamePlayer = (TextView) convertView.findViewById(R.id.tvNamePlayer);
        TextView tvBanThang = (TextView) convertView.findViewById(R.id.tvBanThang);

        tvNumberOfNum.setText(topScores.getTeam_name()+".");
        tvNamePlayer.setText(topScores.getPlayer_name());
        tvBanThang.setText(String.valueOf(topScores.getGoals()));
        return convertView;

    }
}
