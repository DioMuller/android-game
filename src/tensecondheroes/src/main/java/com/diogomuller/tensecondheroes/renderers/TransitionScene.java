package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.game.MinigameInfo;

/**
 * Created by Diogo on 16/11/2014.
 */
public class TransitionScene extends BaseScene {
    private int level;
    private MinigameInfo minigameInfo;

    public TransitionScene(Context context, SceneView view,  int level){
        super(context, view);
        this.level = level;
        AudioController.playSound("Music/Score Time.ogg");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean draw(Canvas canvas, Matrix matrix) {
        return super.draw(canvas, matrix);
    }
}
