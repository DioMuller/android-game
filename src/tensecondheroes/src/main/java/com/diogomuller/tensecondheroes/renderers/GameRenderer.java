package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.core.SceneRenderer;

/**
 * Created by Diogo on 09/11/2014.
 */
public class GameRenderer extends SceneRenderer {
    public GameRenderer(Context context){
        super(context);
        this.showFps = true;
    }
}
