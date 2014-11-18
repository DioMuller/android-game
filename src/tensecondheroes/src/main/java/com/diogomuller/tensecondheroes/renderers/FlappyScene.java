package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Color;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.ParallaxEntity;
import com.diogomuller.gamelib.entities.PhysicsEntity;

/**
 * Created by Diogo on 16/11/2014.
 */
public class FlappyScene extends BaseScene {
    public FlappyScene(Context context) {
        super(context);

        PhysicsEntity testHero = new PhysicsEntity("Sprites/flyinghero.png", 2, BitmapEntity.FrameOrientation.VERTICAL, 0.3f);
        testHero.setPosition(new Vector2(100, 100));
        testHero.setCategoryMask(2);
        testHero.setCollisionMask(4);
        this.addChild(testHero);

        BitmapEntity testGround = new BitmapEntity(Color.argb(255, 0, 255, 128), getSize().getX(), 60);
        testGround.setPosition(new Vector2(getSize().getX() / 2 , getSize().getY() - 15));
        testGround.setCategoryMask(4);
        testGround.setCollisionMask(2);
        this.addChild(testGround);

        ParallaxEntity background = new ParallaxEntity("Images/background_morningsky.png", getSize(), 100.0f );
        this.addChild(background);
    }
}
