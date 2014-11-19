package com.diogomuller.tensecondheroes.entities.space;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.entities.PhysicsEntity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;

/**
 * Created by Diogo on 16/11/2014.
 */
public class SpaceHero extends BitmapEntity {
    private final float RELOAD_TIME = 2.0f;

    private SpaceScene parentScene;
    private boolean moving = false;
    private float shootingTime = 0.0f;
    private float newPosition = -1.0f;
    private float xPosition = 0.0f;

    public SpaceHero(Vector2 position, SpaceScene scene){
        super("Sprites/spacehero.png", 2, FrameOrientation.VERTICAL, 0.3f);

        this.parentScene = scene;

        xPosition = position.getX();

        this.setPosition(position);
        this.setCategoryMask(1);
        this.setContactMask(2);
        this.setCollisionThreshold(8.0f);
    }

    public void activate(boolean value){
        moving = value;

        if(moving){
            shootingTime = 0.0f;
        }
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
            position.setY(newPosition);
            newPosition = -1.0f;
        }

        if( moving ){
            shootingTime -= deltaTime;

            if( shootingTime <= 0.0f ){
                parentScene.addShoot((Vector2) position.clone());
                shootingTime = RELOAD_TIME;
            }
        }
    }

    @Override
    public void onCollision(Entity other) {
        // Do Nothing.
    }
}
