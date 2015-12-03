package com.example;

import java.util.Random;

public class Joker
{
    private String[] mJokes;
    public Joker()
    {
        mJokes = new String[]
                {
                        "Why did the rubber chicken cross the road?\nShe wanted to stretch her legs.",
                        "Why did the Roman chicken cross the road?\nShe was afraid someone would Caesar.",
                        "Why did the chicken cross the playground?\nTo get to the other slide.",
                        "Why did the rooster cross the road?\nTo cockadoodle dooo something.",
                        "Why did the dinosaur cross the road?\nBecause chickens hadn't evolved yet.",
                        "Why did the turtle cross the road?\nTo get to the shell station.",
                        "Why did the horse cross the road?\nBecause the chicken needed a day off.",
                        "Why did the duck cross the road?\nTo prove he wasn't chicken.",
                        "Why did the cow cross the road?\nTo get to the udder side.",
                        "What happened when the elephant crossed the road?\nIt stepped on the chicken."
                };
    }

    public String tellJoke()
    {
        //Return a random joke
        Random wRandom = new Random();
        int randomNumber = wRandom.nextInt(10);
        return mJokes[randomNumber];
    }
}
