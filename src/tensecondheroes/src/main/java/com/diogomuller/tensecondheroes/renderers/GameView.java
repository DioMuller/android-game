package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.core.SceneView;

/**
 * Created by Diogo on 09/11/2014.
 */
public class GameView extends SceneView {
    public GameView(Context context){
        super(context);
        this.showFps = true;
    }
}
