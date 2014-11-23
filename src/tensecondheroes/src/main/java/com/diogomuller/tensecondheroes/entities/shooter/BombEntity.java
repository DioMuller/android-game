package com.diogomuller.tensecondheroes.entities.shooter;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.FlappyScene;
import com.diogomuller.tensecondheroes.renderers.ShooterScene;

/**
 * Created by Diogo on 23/11/2014.
 */
public class BombEntity extends BitmapEntity {
    private static final float IMPULSE_SPEED = 350.0f;
    private static final float FALL_SPEED = 200.0f;

    private float speed = 0.0f;
    private float minHeight = 0.0f;
    private ShooterScene scene;

    public BombEntity(ShooterScene scene, Vector2 position, float minHeight) {
        super("Sprites/bomb.png", 2, FrameOrientation.HORIZONTAL, 0.3f);

        this.scene = scene;

        this.setPosition(position);
        this.setCategoryMask(1);
        this.setContactMask(2);

        this.minHeight = minHeight;

        impulse();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        position.setY(position.getY() + (speed * deltaTime));
        speed += (FALL_SPEED * deltaTime);

        if (position.getY() > minHeight && speed > 0) {
            scene.removeObstacle(this, true);
        }
    }

    public void impulse() {
        AudioController.playSound("Sound/drop.wav");
        speed = -IMPULSE_SPEED;
    }

    @Override
    public void onContact(Entity other) {

    }
}