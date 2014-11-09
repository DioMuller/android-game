package com.diogomuller.tensecondheroes.activities;

import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.tensecondheroes.renderers.GameRenderer;

/**
 * Created by Diogo on 06/11/2014.
 */
public class MainGameActivity extends GameActivity {
    public MainGameActivity() {
        super();

        loadScene(new GameRenderer(this));
    }
}
