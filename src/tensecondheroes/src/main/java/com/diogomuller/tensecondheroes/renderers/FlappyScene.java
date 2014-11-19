package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Color;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.ParallaxEntity;
import com.diogomuller.gamelib.entities.PhysicsEntity;
import com.diogomuller.tensecondheroes.base.BaseScene;

import java.util.List;

/**
 * Created by Diogo on 16/11/2014.
 */
public class FlappyScene extends BaseScene {
    PhysicsEntity testHero;

    public FlappyScene(Context context) {
        super(context);

        testHero = new PhysicsEntity("Sprites/flyinghero.png", 2, BitmapEntity.FrameOrientation.VERTICAL, 0.3f);
        testHero.setPosition(new Vector2(50, 50));
        testHero.setCategoryMask(2);
        testHero.setCollisionMask(4);
        testHero.setCollisionThreshold(8.0f);
        this.addChild(testHero);

        BitmapEntity testGround = new BitmapEntity(Color.argb(255, 0, 255, 128), getSize().getX(), 60);
        testGround.setPosition(new Vector2(getSize().getX() / 2 , getSize().getY() - 15));
        testGround.setCategoryMask(4);
        testGround.setCollisionMask(2);
        this.addChild(testGround);

        ParallaxEntity background = new ParallaxEntity("Images/background_morningsky.png", getSize(), 100.0f );
        this.addChild(background);
    }

    @Override
    public void onTouchEntered(List<Vector2> points) {
        testHero.applyForce(new Vector2(0.0f, -250.0f));
        AudioController.playSound("Sound/drop.wav");
    }
}
