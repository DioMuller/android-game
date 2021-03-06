package com.diogomuller.tensecondheroes.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.tensecondheroes.R;
import com.diogomuller.tensecondheroes.game.MinigameInfo;
import com.diogomuller.tensecondheroes.game.Minigames;

import java.util.List;

public class LevelSelectActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<MinigameInfo> minigames = Minigames.getMinigames();
        MinigameInfo[] arrayInfo = new MinigameInfo[minigames.size()];
        minigames.toArray(arrayInfo);

        ArrayAdapter<MinigameInfo> arrayAdapter = new ArrayAdapter<MinigameInfo>(
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
        Intent intent = new Intent(this, MainGameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Level", position);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
