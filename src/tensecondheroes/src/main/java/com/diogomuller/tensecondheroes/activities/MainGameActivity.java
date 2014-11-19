package com.diogomuller.tensecondheroes.activities;

import android.os.Bundle;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.tensecondheroes.renderers.FlappyScene;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;

/**
 * Created by Diogo on 06/11/2014.
 */
public class MainGameActivity extends GameActivity {
    private final float SPLASH_TIME = 3.0f;
    private final float GAME_TIME = 10.0f;

    private float timeToChange = 0.0f;
    /** Current Player Score. */
    private int score = 0;
    /** Score after last minigame.*/
    private int lastScore = 0;
    /** Number of lives. */
    private int lives = 3;
    /** Is a transition screen? */
    private boolean isTransition = false;
    /** Next level */
    private int nextLevel = 0;

    public MainGameActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        AudioController.playMusic("Music/Save Me.ogg");
        loadScene(new SpaceScene(this));
    }

    //region Game Data
    public int getLives(){
        return lives;
    }

    public int getScore(){
        return score;
    }

    public float getRemainingTime() {
        return timeToChange;
    }

    public void subtractLife(){
        lives--;

        if(lives < 0){
            //TODO: Go to results screen.
        }
    }

    public void addScore(int score){
        this.score += score;
    }

    public void decreaseTimer(float delta){
        timeToChange -= delta;

        if(timeToChange <= 0){
            goToNextLevel();
        }
    }

    public void goToNextLevel(){
        isTransition = !isTransition;

        if(isTransition){
            // TODO: Select next level
            // TODO: Show transition view
        } else {
            // TODO: Load minigame.
        }

    }
    //endregion Game Data
}
