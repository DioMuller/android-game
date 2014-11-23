package com.diogomuller.tensecondheroes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.tensecondheroes.game.HighScores;
import com.diogomuller.tensecondheroes.game.Minigames;
import com.diogomuller.tensecondheroes.renderers.DrivingScene;
import com.diogomuller.tensecondheroes.renderers.FlappyScene;
import com.diogomuller.tensecondheroes.renderers.RunningScene;
import com.diogomuller.tensecondheroes.renderers.ShooterScene;
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

    private int highscore = -1;

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

    public int getHighscore(){
        if( highscore == -1 ) {
            highscore = infiniteMode ? HighScores.getHighscore(nextLevel).getScore() : HighScores.getMainGameHighscore().getScore();
        }

        if( score > highscore ) highscore = score;

        return highscore;
    }

    public float getRemainingTime() {
        return timeToChange;
    }

    public boolean showCounter(){
        return !isTransition && !infiniteMode;
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
            if( !infiniteMode ) {
                HighScores.setHighscore(nextLevel, score - lastScore);
                HighScores.setMainGameHighscore(score);
            } else {
                HighScores.setHighscore(nextLevel, score);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goToNextLevel(){
        isTransition = !isTransition;

        if(isTransition){
            if( !infiniteMode ) {
                HighScores.setHighscore(nextLevel, score - lastScore);
                nextLevel = Minigames.getRandomGame();
            }
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
        lastScore = score;

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
                    case Minigames.SHOOT:
                        loadScene(new ShooterScene(context, screen));
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
