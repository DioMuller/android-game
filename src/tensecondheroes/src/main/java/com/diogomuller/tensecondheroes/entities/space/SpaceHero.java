package com.diogomuller.tensecondheroes.entities.space;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;

/**
 * Created by Diogo on 16/11/2014.
 */
public class SpaceHero extends BitmapEntity {
    private final float RELOAD_TIME = 1.0f;
    private final float TOUCH_THRESHOLD = 50.0f;

    private SpaceScene parentScene;
    private boolean moving = false;
    private float shootingTime = 0.0f;
    private float newPosition = -1.0f;
    private float xPosition = 0.0f;

    public SpaceHero(SpaceScene scene, Vector2 position ){
        super("Sprites/spacehero.png", 2, FrameOrientation.VERTICAL, 0.3f);

        this.parentScene = scene;

        xPosition = position.getX();

        this.setPosition(position);
        this.setCategoryMask(1);
        this.setContactMask(2);
        this.setCollisionThreshold(8.0f);
    }

    public void activate(boolean value){
        if( value )shootingTime = 0.0f;
        else moving = false;
    }

    public boolean isMoving(){
        return moving;
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

        if( moving ){
            shootingTime -= deltaTime;

            if( shootingTime <= 0.0f ){
                parentScene.addShoot(position);
                shootingTime = RELOAD_TIME;
            }
        }
    }
}
