package com.diogomuller.tensecondheroes.entities.running;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.RunningScene;

/**
 * Created by Diogo on 16/11/2014.
 */
public class RunningHero extends BitmapEntity {
    private static final float IMPULSE_SPEED = -200.0f;
    private static final float FALL_SPEED = -250.0f;

    private float speed = 0.0f;
    private RunningScene scene;
    private float groundHeight;
    private boolean isOnGround = false;

    public RunningHero(RunningScene scene, Vector2 position, float groundHeight){
        super("Sprites/runningirl.png", 4, FrameOrientation.VERTICAL, 0.3f);

        this.scene = scene;

        this.setPosition(position);
        this.setCategoryMask(1);
        this.setContactMask(6);
        this.setCollisionThreshold(4.0f);
        this.groundHeight = groundHeight - (this.size.getY() / 2.0f);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        position.setY(position.getY() + (speed * deltaTime));
        speed -= (FALL_SPEED * deltaTime);

        if( position.getY() < 0 ) position.setY(0);

        if( position.getY() > groundHeight ) {
            position.setY(groundHeight);
            isOnGround = true;
        }
    }

    public void impulse(){
        if( isOnGround ) {
            speed = IMPULSE_SPEED;
        }

    }
}
