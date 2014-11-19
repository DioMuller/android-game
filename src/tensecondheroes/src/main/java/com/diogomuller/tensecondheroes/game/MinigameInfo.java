package com.diogomuller.tensecondheroes.game;

/**
 * Created by Diogo on 19/11/2014.
 */
public class MinigameInfo {
    private String name;
    private String hero;
    private String image;

    public MinigameInfo(String name, String hero, String image){
        this.name = name;
        this.hero = hero;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public String getHero(){
        return hero;
    }

    public String getImage(){
        return image;
    }
}