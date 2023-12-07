package com.example.ballz.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ballz.Model.GoalScores;
import com.example.ballz.R;

import java.util.ArrayList;

public class CustomGoalScores extends ArrayAdapter {
    Context context;
    int layoutItem;

    ArrayList<GoalScores> lsTopScores = new ArrayList<>();

    public CustomGoalScores(@NonNull Context context, int resource, ArrayList<GoalScores> lsTopScores) {
        super(context, resource, lsTopScores);
        this.context = context;
        this.layoutItem = resource;
        this.lsTopScores = lsTopScores;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GoalScores topScores = lsTopScores.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }


        TextView tvNumberOfNum = (TextView) convertView.findViewById(R.id.tvNumberOfNum);
        TextView tvNamePlayer = (TextView) convertView.findViewById(R.id.tvNamePlayer);
        TextView tvBanThang = (TextView) convertView.findViewById(R.id.tvBanThang);

        tvNumberOfNum.setText(topScores.getRank()+".");
        tvNamePlayer.setText(topScores.getPlayer_name());
        tvBanThang.setText(String.valueOf(topScores.getGoal()));
        return convertView;
    }
}
