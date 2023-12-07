package com.example.ballz.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ballz.Model.Match;
import com.example.ballz.R;

import java.util.List;

public class CustomAdapterMatchMain extends RecyclerView.Adapter<CustomAdapterMatchMain.ViewHolder> {
    Context context;
    private List<Match> itemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Match match);
    }

    public CustomAdapterMatchMain(List<Match> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView time, scoreHome, scoreAway;
        ImageView iconAway, iconHome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tvTime);
            scoreHome = itemView.findViewById(R.id.tvScoreHome);
            scoreAway = itemView.findViewById(R.id.tvScoreAway);
            iconAway = itemView.findViewById(R.id.logoAway);
            iconHome = itemView.findViewById(R.id.logoHome);
            itemView.setOnClickListener(this);
        }

        public void bind(Match match) {
            time.setText(match.getTime());
            scoreHome.setText(match.getScoreHome());
            scoreAway.setText(match.getScoreAway());
            checkIcon(match.getLogoHome(), iconHome);
            checkIcon(match.getLogoAway(), iconAway);
        }

        private void checkIcon(String icon, ImageView view) {
            switch (icon) {
                case "Man City ":
                    System.out.println("Da vao mancity");
                    view.setImageResource(R.drawable.manchester_city);
                    break;
                case "Chelsea":
                    view.setImageResource(R.drawable.chelsea);
                    System.out.println("Da vao chelsea");
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
                case "Aston Villla":
                    view.setImageResource(R.drawable.astonvilla);
                    break;
                case "Fullham":
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
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                Match clickedMatch = itemList.get(position);
                listener.onItemClick(clickedMatch);
            }
        }
    }
}
