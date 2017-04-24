package com.micste.busdriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by micste on 2017-03-20.
 */

public class Deck {

    private ArrayList<Card> cards;
    private int count;
    private int test;

    public Deck(){

        count = 0;
        cards = new ArrayList<>();

        //Clubs
        cards.add(new Card(R.drawable.clubs_2, 2, "Black"));
        cards.add(new Card(R.drawable.clubs_3, 3, "Black"));
        cards.add(new Card(R.drawable.clubs_4, 4, "Black"));
        cards.add(new Card(R.drawable.clubs_5, 5, "Black"));
        cards.add(new Card(R.drawable.clubs_6, 6, "Black"));
        cards.add(new Card(R.drawable.clubs_7, 7, "Black"));
        cards.add(new Card(R.drawable.clubs_8, 8, "Black"));
        cards.add(new Card(R.drawable.clubs_9, 9, "Black"));
        cards.add(new Card(R.drawable.clubs_10, 10, "Black"));
        cards.add(new Card(R.drawable.clubs_j, 11, "Black"));
        cards.add(new Card(R.drawable.clubs_q, 12, "Black"));
        cards.add(new Card(R.drawable.clubs_k, 13, "Black"));
        cards.add(new Card(R.drawable.clubs_a, 1, "Black"));

        //Spades
        cards.add(new Card(R.drawable.spades_2, 2, "Black"));
        cards.add(new Card(R.drawable.spades_3, 3, "Black"));
        cards.add(new Card(R.drawable.spades_4, 4, "Black"));
        cards.add(new Card(R.drawable.spades_5, 5, "Black"));
        cards.add(new Card(R.drawable.spades_6, 6, "Black"));
        cards.add(new Card(R.drawable.spades_7, 7, "Black"));
        cards.add(new Card(R.drawable.spades_8, 8, "Black"));
        cards.add(new Card(R.drawable.spades_9, 9, "Black"));
        cards.add(new Card(R.drawable.spades_10, 10, "Black"));
        cards.add(new Card(R.drawable.spades_j, 11, "Black"));
        cards.add(new Card(R.drawable.spades_q, 12, "Black"));
        cards.add(new Card(R.drawable.spades_k, 13, "Black"));
        cards.add(new Card(R.drawable.spades_a, 1, "Black"));

        //Hearts
        cards.add(new Card(R.drawable.hearts_2, 2, "Red"));
        cards.add(new Card(R.drawable.hearts_3, 3, "Red"));
        cards.add(new Card(R.drawable.hearts_4, 4, "Red"));
        cards.add(new Card(R.drawable.hearts_5, 5, "Red"));
        cards.add(new Card(R.drawable.hearts_6, 6, "Red"));
        cards.add(new Card(R.drawable.hearts_7, 7, "Red"));
        cards.add(new Card(R.drawable.hearts_8, 8, "Red"));
        cards.add(new Card(R.drawable.hearts_9, 9, "Red"));
        cards.add(new Card(R.drawable.hearts_10, 10, "Red"));
        cards.add(new Card(R.drawable.hearts_j, 11, "Red"));
        cards.add(new Card(R.drawable.hearts_q, 12, "Red"));
        cards.add(new Card(R.drawable.hearts_k, 13, "Red"));
        cards.add(new Card(R.drawable.hearts_a, 1, "Red"));

        //Diamonds
        cards.add(new Card(R.drawable.diamonds_2, 2, "Red"));
        cards.add(new Card(R.drawable.diamonds_3, 3, "Red"));
        cards.add(new Card(R.drawable.diamonds_4, 4, "Red"));
        cards.add(new Card(R.drawable.diamonds_5, 5, "Red"));
        cards.add(new Card(R.drawable.diamonds_6, 6, "Red"));
        cards.add(new Card(R.drawable.diamonds_7, 7, "Red"));
        cards.add(new Card(R.drawable.diamonds_8, 8, "Red"));
        cards.add(new Card(R.drawable.diamonds_9, 9, "Red"));
        cards.add(new Card(R.drawable.diamonds_10, 10, "Red"));
        cards.add(new Card(R.drawable.diamonds_j, 11, "Red"));
        cards.add(new Card(R.drawable.diamonds_q, 12, "Red"));
        cards.add(new Card(R.drawable.diamonds_k, 13, "Red"));
        cards.add(new Card(R.drawable.diamonds_a, 1, "Red"));

    }

    public void increaseTestInt() {

        test++;

    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Card getCardTest() {

        count++;

        return cards.remove(test);

    }

    public Card randomCard() {

        count++;

        Random random = new Random();
        int i = random.nextInt(cards.size());

        return cards.remove(i);
    }

    public String getSize() {

        return "" + cards.size();

    }

    public boolean isEmpty() {

        boolean isEmpty = false;

        if (cards.isEmpty()) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public int getCount() {

        return count;
    }

}
