package com.diogomuller.tensecondheroes.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.diogomuller.gamelib.core.Assets;
import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.tensecondheroes.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Assets.setAssetManager(this.getAssets());
        AudioController.playMusic("Music/Press Start.ogg");

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AudioController.pauseMusic();

        if(isFinishing())
            AudioController.stopMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AudioController.resumeMusic();
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
