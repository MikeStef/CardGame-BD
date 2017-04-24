package com.micste.busdriver;

/**
 * Created by micste on 2017-04-14.
 */

public class Player {

    private String name;
    private int score;

    //tom konstruktör för firebase
    public Player() {

    }

    public Player(String name, int score) {

        this.name = name;
        this.score = score;

    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

}
