package com.micste.busdriver;

/**
 * Created by micste on 2017-03-20.
 */

public class Card {
    private int image;
    private int value;
    private String color;

    public Card(int image, int value, String color){
        this.image = image;
        this.value = value;
        this.color = color;
    }

    public int getImage(){
        return image;
    }
    public int getValue(){
        return value;
    }
    public String getColor(){
        return color;
    }

    //För test atm
    public String toString(){
        String cardImage = "Image src: " + image;

        return cardImage;
    }
}
