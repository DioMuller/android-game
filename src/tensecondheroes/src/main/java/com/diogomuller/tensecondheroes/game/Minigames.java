package com.diogomuller.tensecondheroes.game;

import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Diogo on 19/11/2014.
 */

public class Minigames {
    //region Static
    private static Random rng = new Random();

    public static final int SPACE = 0;
    public static final int DRIVE = 1;
    public static final int FLAPPY = 2;
    public static final int RUN = 3;
    public static final int SHOOT = 4;

    public static final int COUNT = 5;


    public static int getRandomGame(){
        return Math.abs(rng.nextInt() % COUNT);
    }

    public static MinigameInfo getInfo(int minigame){
        switch (minigame){
            case SPACE:
                return new MinigameInfo("Explore Space!", "Captain Space", "Sprites/spacehero_thumb.png");
            case DRIVE:
                return new MinigameInfo("Avoid the Civilians!", "Forklift Driver", "Sprites/worker_thumb.png");
            case FLAPPY:
                return new MinigameInfo("Flap!", "Towelman", "Sprites/flyinghero_thumb.png");
            case RUN:
                return new MinigameInfo("Run!", "RunninGirl", "Sprites/runningirl_thumb.png");
            case SHOOT:
                return new MinigameInfo("Shoot!", "El Vaquero", "Sprites/shooter_thumb.png");
            default:
                return null;
        }
    }

    public static List<MinigameInfo> getMinigames(){
        List<MinigameInfo> result = new ArrayList<MinigameInfo>();
        for(int i = 0; i < COUNT; i++) {
            result.add(getInfo(i));
        }

        return result;
    }
    //endregion Static
}
