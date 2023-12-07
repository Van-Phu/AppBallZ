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

import com.example.ballz.Model.Coach;
import com.example.ballz.Model.Player;
import com.example.ballz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterPlayer extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Player> lsPlayer = new ArrayList<>();
    public CustomAdapterPlayer(@NonNull Context context, int resource, ArrayList<Player> lsPlayer) {
        super(context, resource, lsPlayer);
        this.context = context;
        this.layoutItem = resource;
        this.lsPlayer = lsPlayer;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Player player = lsPlayer.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);
        }
        ImageView imgPlayer = (ImageView) convertView.findViewById(R.id.imgPlayer);
        TextView tvNamePlayerClub = (TextView) convertView.findViewById(R.id.tvNamePlayerClub);
        TextView tvCountryClub = (TextView) convertView.findViewById(R.id.tvCountryClub);
        String str = "https://images.fotmob.com/image_resources/playerimages/" + player.getIdPlayer() + ".png";
        tvNamePlayerClub.setText(player.getNamePlayer());
        tvCountryClub.setText(player.getCountryName());
        Picasso.get().load(str).resize(50, 50).into(imgPlayer);
        return convertView;
    }
}
