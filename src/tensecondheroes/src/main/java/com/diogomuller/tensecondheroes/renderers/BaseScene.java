package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.physics.Physics;

import java.util.List;

/**
 * Created by Diogo on 09/11/2014.
 */
public class BaseScene extends SceneView {
    private Physics physics = Physics.getInstance();
    private static final float HEIGHT = 320.0f;

    public BaseScene(Context context){
        super(context, HEIGHT);

        this.showFps = true;

        physics.setGravity(new Vector2(0, 5));
        physics.setPixelsPerMeter(4);
    }
}
