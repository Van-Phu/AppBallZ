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

import com.example.ballz.Model.ClubStanding;
import com.example.ballz.Model.Coach;
import com.example.ballz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterCoach extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Coach> lsCoach = new ArrayList<>();
    public CustomAdapterCoach(@NonNull Context context, int resource, ArrayList<Coach> lsCoach) {
        super(context, resource, lsCoach);
        this.context = context;
        this.layoutItem = resource;
        this.lsCoach = lsCoach;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Coach coach = lsCoach.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }
        ImageView imgCoach = (ImageView) convertView.findViewById(R.id.imgCoach);
        TextView tvNameCoach = (TextView) convertView.findViewById(R.id.tvNameCoach);
        Picasso.get().load(coach.getImgCoach()).resize(50, 50).into(imgCoach);
        tvNameCoach.setText(coach.getNameCoach());
        return convertView;
    }
}
