package com.micste.busdriver;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by micste on 2017-04-14.
 */

public class DatabaseHandler {

    private DatabaseReference mDatabase;
    private ArrayList<Player> players;
    private Player player;

    public DatabaseHandler() {

        this.mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void writeNewHighscore(String name, int score) {

        Player player = new Player(name, score);

        mDatabase.child("players").push().setValue(player);

    }

    public ArrayList<Player> getHighscoreData() {

        players = new ArrayList<>();

        Query query = mDatabase.child("players").orderByChild("score").limitToLast(10);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot scoreDataSnapshot: dataSnapshot.getChildren()) {
                    player = scoreDataSnapshot.getValue(Player.class);
                    players.add(player);
                    Log.d("TEST", "Player name: " + players.get(0).getName());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return players;

    }
}
