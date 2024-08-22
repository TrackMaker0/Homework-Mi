package com.example.homeworkday04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameRecyclerAdapter extends RecyclerView.Adapter<GameRecyclerAdapter.ViewHolder> {

    private List<GameBean> data;

    public GameRecyclerAdapter(List<GameBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameRecyclerAdapter.ViewHolder holder, int position) {
        GameBean game = data.get(position);
        holder.mGameIcon.setImageResource(game.getGameIcon());
        holder.mGameName.setText(game.getGameName());
        holder.mGameButton.setText(game.getGameStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mGameIcon;
        TextView mGameName;
        Button mGameButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mGameIcon = itemView.findViewById(R.id.game_icon);
            mGameName = itemView.findViewById(R.id.game_name);
            mGameButton = itemView.findViewById(R.id.game_btn);
        }
    }
}
