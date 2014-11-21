package com.diogomuller.tensecondheroes.entities.flappy;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.FlappyScene;

/**
 * Created by Diogo on 16/11/2014.
 */
public class FlappyHero extends BitmapEntity {
    private static final float IMPULSE_SPEED = -150.0f;
    private static final float FALL_SPEED = -200.0f;

    private float speed = 0.0f;
    private boolean active = false;
    private FlappyScene scene;

    public FlappyHero(FlappyScene scene, Vector2 position){
        super("Sprites/flyinghero.png", 2, BitmapEntity.FrameOrientation.VERTICAL, 0.3f);

        this.scene = scene;

        this.setPosition(position);
        this.setCategoryMask(1);
        this.setContactMask(6);
        this.setCollisionThreshold(8.0f);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(active){
            position.setY(position.getY() + (speed * deltaTime));
            speed -= (FALL_SPEED * deltaTime);

            if( position.getY() < 0 ) position.setY(0);
        }
    }

    public void impulse(){
        active = true;
        AudioController.playSound("Sound/drop.wav");
        speed = IMPULSE_SPEED;

    }

    @Override
    public void onContact(Entity other) {
        active = false;
        scene.endScene();
    }
}
