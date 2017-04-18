package com.micste.busdriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by micste on 2017-04-15.
 */

public class HighscoreAdapter extends ArrayAdapter<Player> {

    private Context context;
    private ArrayList<Player> players;

    private static class ViewHolder {
        private TextView nameView;
        private TextView scoreView;
    }

    public HighscoreAdapter(Context context, ArrayList<Player> players) {
        super(context, R.layout.list_highscore, players);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
            .inflate(R.layout.list_highscore, parent, false);

            vh = new ViewHolder();
            vh.nameView = (TextView) convertView.findViewById(R.id.list_playername);
            vh.scoreView = (TextView) convertView.findViewById(R.id.list_score);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

         Player player = players.get(position);
        if (player != null) {
            vh.nameView.setText(player.getName());
            vh.scoreView.setText(String.valueOf(player.getScore()));
        }

        return convertView;
    }

}
