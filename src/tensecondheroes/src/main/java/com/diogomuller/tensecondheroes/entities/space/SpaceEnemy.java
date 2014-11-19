package com.diogomuller.tensecondheroes.entities.space;

import android.graphics.Color;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;

import java.util.Vector;

/**
 * Created by Diogo on 19/11/2014.
 */
public class SpaceEnemy extends BitmapEntity {
    float lifetime;
    float speed;
    SpaceScene scene;

    public SpaceEnemy(SpaceScene scene, Vector2 position, float speed, float lifetime) {
        super("Sprites/spaceenemy.png", 4, FrameOrientation.VERTICAL, 0.3f );
        this.lifetime = lifetime;
        this.speed = speed;
        this.scene = scene;
        this.position = position;

        setCategoryMask(2);
        setContactMask(5);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifetime -= deltaTime;

        if(lifetime < 0) scene.removeChild(this);

        this.position.setX(position.getX() - (speed * deltaTime));
    }

    @Override
    public void onContact(Entity other) {
        scene.removeEnemy(this, other);
    }
}