package com.diogomuller.tensecondheroes.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.tensecondheroes.game.HighScores;
import com.diogomuller.tensecondheroes.game.HighscoreInfo;

import java.util.List;

/**
 * Created by Diogo on 22/11/2014.
 */
public class HighscoreAcitivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<HighscoreInfo> highscores = HighScores.getHighscoreList();
        HighscoreInfo[] arrayInfo = new HighscoreInfo[highscores.size()];
        highscores.toArray(arrayInfo);

        ArrayAdapter<HighscoreInfo> arrayAdapter = new ArrayAdapter<HighscoreInfo>(
                this,
                android.R.layout.simple_list_item_1,
                arrayInfo);

        setListAdapter(arrayAdapter);
        AudioController.playMusic("Music/Credits.mp3");
    }

    @Override
    protected void onPause() {
        super.onPause();

        AudioController.stopMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AudioController.playMusic("Music/Credits.mp3");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
    }
}