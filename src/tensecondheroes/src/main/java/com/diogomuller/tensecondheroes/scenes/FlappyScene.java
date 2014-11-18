package com.diogomuller.tensecondheroes.scenes;

import android.graphics.Color;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.BasicNode;
import com.diogomuller.gamelib.node.BitmapNode;
import com.diogomuller.gamelib.node.ParallaxNode;
import com.diogomuller.gamelib.node.PhysicsNode;

import java.util.Vector;

/**
 * Created by Diogo on 16/11/2014.
 */
public class FlappyScene extends BasicNode {
    public FlappyScene(Vector2 size) {
        super();

        PhysicsNode testHero = new PhysicsNode("Sprites/flyinghero.png", 2, BitmapNode.FrameOrientation.VERTICAL, 0.3f);
        testHero.setPosition(new Vector2(100, 100));
        testHero.setCategoryMask(1);
        testHero.setCollisionMask(2);
        this.addChild(testHero);

        BitmapNode testGround = new BitmapNode(Color.argb(255, 0, 255, 128), size.getX(), 60);
        testGround.setPosition(new Vector2(size.getX() / 2 , size.getY() - 30));
        testGround.setCategoryMask(2);
        testGround.setCollisionMask(1);
        this.addChild(testGround);

        ParallaxNode background = new ParallaxNode("Images/background_morningsky.png", size, 100.0f );
        this.addChild(background);
    }
}
