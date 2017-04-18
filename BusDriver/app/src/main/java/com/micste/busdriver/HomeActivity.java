package com.micste.busdriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class HomeActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startGame(View view) {

        view.startAnimation(buttonClick);

        Intent intent = new Intent(HomeActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void showHighscore(View view) {

        view.startAnimation(buttonClick);

        Intent intent = new Intent(HomeActivity.this, HighscoreActivity.class);

        startActivity(intent);

    }

    public void showHelp(View view) {

        view.startAnimation(buttonClick);

        Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
        startActivity(intent);

    }
}
