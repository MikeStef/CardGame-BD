package com.micste.busdriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class HighscoreActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Query query;
    private FirebaseListAdapter mAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        ListView highscoreList = (ListView) findViewById(R.id.lv_highscore);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        query = mDatabase.child("players").orderByChild("score").limitToLast(10);

        mAdapter = new FirebaseListAdapter<Player>(this, Player.class, R.layout.list_highscore, query) {
            @Override
            protected void populateView(View view, Player player, int position) {
                ((TextView)view.findViewById(R.id.list_playername)).setText(player.getName());
                ((TextView)view.findViewById(R.id.list_score)).setText(String.valueOf(player.getScore()));
                progressBar.setVisibility(View.GONE);
            }
        };
        highscoreList.setAdapter(mAdapter);

    }
}
