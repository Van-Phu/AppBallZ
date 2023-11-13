package com.example.ballz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import java.util.List;

public class MatchMainAdapter extends RecyclerView.Adapter<MatchMainAdapter.ViewHolder> {
    private List<Match> itemList;

    public MatchMainAdapter(List<Match> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_fight_layout_custom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = itemList.get(position);
        holder.bind(match);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameAway, nameHome, time, scoreHome, scoreAway;
        ImageView iconAway, iconHome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAway = itemView.findViewById(R.id.tvAway);
            nameHome = itemView.findViewById(R.id.tvHome);
            time = itemView.findViewById(R.id.tvTime);
            scoreHome = itemView.findViewById(R.id.tvScoreHome);
            scoreAway = itemView.findViewById(R.id.tvScoreAway);
            iconAway = itemView.findViewById(R.id.logoAway);
            iconHome = itemView.findViewById(R.id.logoHome);
        }

        public void bind(Match match) {
            nameAway.setText(match.getLogoAway());
            nameHome.setText(match.getLogoHome());
            time.setText(match.getTime());
            scoreHome.setText(match.getScoreHome());
            scoreAway.setText(match.getScoreAway());

//            Glide.with(itemView.getContext()).load(match.getLogoAway()).into(iconAway);
//            Glide.with(itemView.getContext()).load(match.getLogoHome()).into(iconHome);
        }
    }
}
