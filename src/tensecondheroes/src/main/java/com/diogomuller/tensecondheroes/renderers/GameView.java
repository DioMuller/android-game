package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.BitmapNode;
import com.diogomuller.gamelib.node.PhysicsNode;

/**
 * Created by Diogo on 09/11/2014.
 */
public class GameView extends SceneView {
    public GameView(Context context){
        super(context, 320.0f);

        this.showFps = true;

        PhysicsNode testHero = new PhysicsNode("Sprites/flyinghero.png", 2, BitmapNode.FrameOrientation.VERTICAL);
        testHero.setPosition(new Vector2(100, 100));
        this.addChild(testHero);
    }
}
