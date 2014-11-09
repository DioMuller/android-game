package com.diogomuller.tensecondheroes.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.diogomuller.tensecondheroes.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newgameClick(View v) {
        Intent intent = new Intent(this, MainGameActivity.class);
        startActivity(intent);
    }

    public void loadlevelClick(View v) {

    }

    public void highscoreClick(View v) {

    }
}
