package com.diogomuller.tensecondheroes.entities.forklift;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.DrivingScene;

/**
 * Created by Diogo on 16/11/2014.
 */
public class ForkliftHero extends BitmapEntity {
    private final float TOUCH_THRESHOLD = 50.0f;

    private DrivingScene parentScene;
    private boolean moving = false;
    private float newPosition = -1.0f;

    public ForkliftHero(DrivingScene scene, Vector2 position ){
        super("Sprites/worker.png", 2, FrameOrientation.HORIZONTAL, 0.3f);

        this.parentScene = scene;

        this.setPosition(position);
        this.setCategoryMask(1);
        this.setContactMask(2);
        this.setCollisionThreshold(8.0f);
    }

    public void moveToY(float y){
        newPosition = y;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if( newPosition > 0.0f ){
            if( Math.abs(newPosition - position.getY()) < TOUCH_THRESHOLD ) {
                moving = true;
                position.setY(newPosition);
            }
            else moving = false;

            newPosition = -1.0f;
        }
    }
}
