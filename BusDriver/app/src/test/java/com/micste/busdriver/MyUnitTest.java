package com.micste.busdriver;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by micste on 2017-04-11.
 */

public class MyUnitTest {

    @Test
    public void testDeckSize_IsDecreasing() {

        Deck deck = new Deck();
        deck.randomCard();

        String result = deck.getSize();

        assertEquals("Size not decreasing: ", "51", result);

    }

    @Test
    public void testCounter() {

        int expectedCount = 3;

        Deck deck = new Deck();

        for (int i = 0; i < expectedCount; i++) {
            deck.randomCard();
        }

        int result = deck.getCount();

        assertEquals("Count not increasing to 1: ", expectedCount, result);

    }

    @Test
    public void testDeckIsEmpty_ReturnsTrue() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.randomCard();
        }

        assertThat(deck.isEmpty(), is(true));

    }

}
