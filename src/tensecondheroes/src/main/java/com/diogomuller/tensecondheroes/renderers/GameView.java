package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Color;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.BitmapNode;
import com.diogomuller.gamelib.node.PhysicsNode;
import com.diogomuller.gamelib.physics.Physics;

import java.util.List;

/**
 * Created by Diogo on 09/11/2014.
 */
public class GameView extends SceneView {
    private Physics physics = Physics.getInstance();
    private static final float HEIGHT = 640.0f;

    public GameView(Context context){
        super(context, HEIGHT);

        this.showFps = true;

        physics.setGravity(new Vector2(0, 5));
        physics.setPixelsPerMeter(4);

        PhysicsNode testHero = new PhysicsNode("Sprites/flyinghero.png", 2, BitmapNode.FrameOrientation.VERTICAL, 0.3f);
        testHero.setPosition(new Vector2(100, 100));
        testHero.setCategoryMask(1);
        testHero.setCollisionMask(2);
        this.addChild(testHero);

        BitmapNode testGround = new BitmapNode(Color.argb(255, 0, 255, 128), getSize().getX(), 60);
        testGround.setPosition(new Vector2(getSize().getX() / 2 , HEIGHT - 30));
        testGround.setCategoryMask(2);
        testGround.setCollisionMask(1);
        this.addChild(testGround);
    }

    @Override
    public void onTouchEntered(List<Vector2> points) {
        AudioController.playSound("Sound/drop.wav");
    }
}
