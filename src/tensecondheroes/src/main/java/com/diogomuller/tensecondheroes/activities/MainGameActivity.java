package com.diogomuller.tensecondheroes.activities;

import android.os.Bundle;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.tensecondheroes.renderers.FlappyScene;

/**
 * Created by Diogo on 06/11/2014.
 */
public class MainGameActivity extends GameActivity {
    public MainGameActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        AudioController.playMusic("Music/Save Me.ogg");
        loadScene(new FlappyScene(this));
    }
}
