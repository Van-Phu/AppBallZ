package com.example.ballz.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ballz.Model.TimeLine;
import com.example.ballz.R;

import java.util.ArrayList;

public class CustomAdapterTimeline extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<TimeLine> lsTimeline = new ArrayList<>();
    public CustomAdapterTimeline(@NonNull Context context, int resource, ArrayList<TimeLine> lsTimeline) {
        super(context, resource, lsTimeline);

        this.context = context;
        this.layoutItem = resource;
        this.lsTimeline = lsTimeline;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TimeLine timeLine = lsTimeline.get(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }


        TextView homeTimeline = (TextView) convertView.findViewById(R.id.homeTimeline);
        TextView awayTimeline = (TextView) convertView.findViewById(R.id.awayTimeline);

        TextView tvTimeLine = (TextView) convertView.findViewById(R.id.tvTimeLine);






        String side = timeLine.getSide();
        if(side == "home"){
            homeTimeline.setText(timeLine.getHomeName());
            tvTimeLine.setText(timeLine.getTime());
        } else if (side == "away") {
            awayTimeline.setText(timeLine.getAwayName());
            tvTimeLine.setText(timeLine.getTime());
        }
        return convertView;

    }
}
