package com.diogomuller.tensecondheroes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.tensecondheroes.game.Minigames;
import com.diogomuller.tensecondheroes.renderers.DrivingScene;
import com.diogomuller.tensecondheroes.renderers.FlappyScene;
import com.diogomuller.tensecondheroes.renderers.RunningScene;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;
import com.diogomuller.tensecondheroes.renderers.TransitionScene;

/**
 * Created by Diogo on 06/11/2014.
 */
public class MainGameActivity extends GameActivity {
    private final float SPLASH_TIME = 3.0f;
    private final float GAME_TIME = 10.0f;

    private float timeToChange = 10.0f;
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

    private boolean infiniteMode = false;

    public MainGameActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        Bundle extras = getIntent().getExtras();

        if( extras != null && extras.containsKey("Level") ){
            nextLevel = extras.getInt("Level");
            lives = 0;
            infiniteMode = true;
        }

        goToNextLevel();
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

    public boolean showCounter(){
        return !isTransition && !infiniteMode;
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
            if( !infiniteMode ) {
                goToNextLevel();
            } else if( infiniteMode && isTransition) {
                goToNextLevel();
            }
        }
    }

    public void dieAndChangeLevel(){
        lives--;

        if( lives >= 0) {
            goToNextLevel();
        } else {
            //TODO: Game Over.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goToNextLevel(){
        isTransition = !isTransition;

        if(isTransition){
            if( !infiniteMode ) nextLevel = Minigames.getRandomGame();
            timeToChange = SPLASH_TIME;
            loadTransition();
        } else {
            timeToChange = GAME_TIME;
            loadNextLevel();
        }
    }

    public void loadTransition(){
        final Context context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadScene(new TransitionScene(context, screen, nextLevel));
            }
        });
    }

    public void loadNextLevel(){
        final Context context = this;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (nextLevel){
                    case Minigames.SPACE:
                        loadScene(new SpaceScene(context, screen));
                        break;
                    case Minigames.DRIVE:
                        loadScene(new DrivingScene(context, screen));
                        break;
                    case Minigames.FLAPPY:
                        loadScene(new FlappyScene(context, screen));
                        break;
                    case Minigames.RUN:
                        loadScene(new RunningScene(context, screen));
                        break;
                    default:
                        Log.e("Level Loading", "Error: Level does not exist.");
                        goToNextLevel();
                        break;
                }
            }
        });

    }
    //endregion Game Data
}
