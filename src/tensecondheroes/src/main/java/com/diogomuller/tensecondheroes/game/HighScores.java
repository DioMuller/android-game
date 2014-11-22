package com.diogomuller.tensecondheroes.game;

import android.content.Context;
import android.content.SharedPreferences;

import com.diogomuller.tensecondheroes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diogo on 22/11/2014.
 */
public class HighScores {
    private static SharedPreferences preferences = null;

    public static void initialize(Context context){
        preferences = context.getSharedPreferences(context.getString(R.string.key_highscores), Context.MODE_PRIVATE);
    }

    public static HighscoreInfo getHighscore(int id){
        if( preferences == null ) return null;
        if( id < -1 || id >= Minigames.COUNT) return null;
        if( id == -1 ) return getMainGameHighscore();

        return new HighscoreInfo(id, preferences.getInt("Highscore_" + id, 0));
    }

    public static HighscoreInfo getMainGameHighscore(){
        if( preferences == null ) return null;
        return new HighscoreInfo(-1, preferences.getInt("Highscore_Main", 0));
    }

    public static void setHighscore(int id, int highscore){
        if( preferences == null ) return;
        if( id < -1 || id >= Minigames.COUNT) return;
        if( id == -1 ) {
            setMainGameHighscore(highscore);
            return;
        }

        if( highscore > getHighscore(id).getScore()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("Highscore_" + id, highscore);
            editor.commit();
        }
    }

    public static void setMainGameHighscore(int highscore){
        if( preferences == null ) return;

        if( highscore > getMainGameHighscore().getScore()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("Highscore_Main", highscore);
            editor.commit();
        }
    }

    public static List<HighscoreInfo> getHighscoreList(){
        List<HighscoreInfo> result = new ArrayList<HighscoreInfo>();

        result.add(getMainGameHighscore());

        for(int i = 0; i < Minigames.COUNT; i++){
            result.add(getHighscore(i));
        }

        return result;
    }
}
