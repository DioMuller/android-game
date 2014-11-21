package com.diogomuller.tensecondheroes.entities.running;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.RunningScene;

/**
 * Created by Diogo on 21/11/2014.
 */
public class RunningObstacle extends BitmapEntity {
    float lifetime;
    float speed;
    RunningScene scene;
    boolean scored = false;

    public RunningObstacle(RunningScene scene, Vector2 position, float speed, float lifetime) {
        super("Sprites/thief01.png", 1, FrameOrientation.HORIZONTAL, 0.0f);
        this.lifetime = lifetime;
        this.speed = speed;
        this.scene = scene;
        this.position = position;

        // Size Adjustment.
        this.position.setY(this.position.getY() - (this.size.getY() / 2.0f));

        setCategoryMask(2);
        setContactMask(1);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifetime -= deltaTime;

        if(lifetime < 0) scene.removeChild(this);

        this.position.setX(position.getX() - (speed * deltaTime));

        if( this.position.getX() <= 50 && !scored){
            scored = true;
            scene.addScore();
        }

        if( this.position.getX() < -size.getX()){
            scene.removeObstacle(this, null);
        }
    }

    @Override
    public void onContact(Entity other) {
        scene.removeObstacle(this, other);
    }
}
