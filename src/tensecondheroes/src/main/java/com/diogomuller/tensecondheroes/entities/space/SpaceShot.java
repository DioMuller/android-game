package com.diogomuller.tensecondheroes.entities.space;

import android.graphics.Color;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.tensecondheroes.renderers.SpaceScene;

/**
 * Created by Diogo on 19/11/2014.
 */
public class SpaceShot extends BitmapEntity {
    float lifetime;
    float speed;
    SpaceScene scene;

    public SpaceShot(SpaceScene scene, float speed, float lifetime) {
        super(Color.RED, 10, 5);
        this.lifetime = lifetime;
        this.speed = speed;
        this.scene = scene;

        setCategoryMask(4);
        setContactMask(2);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifetime -= deltaTime;

        if(lifetime < 0) scene.removeChild(this);

        this.position.setX(position.getX() + (speed * deltaTime));
    }
}
