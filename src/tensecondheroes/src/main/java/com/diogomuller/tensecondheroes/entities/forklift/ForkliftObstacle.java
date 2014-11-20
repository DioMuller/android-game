package com.diogomuller.tensecondheroes.entities.forklift;

import android.graphics.Color;

import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.renderers.DrivingScene;

import java.util.Random;

/**
 * Created by Diogo on 20/11/2014.
 */
public class ForkliftObstacle extends BitmapEntity {
    //region Possible Obstacles
    private static final String[] obstacles = {"boycitizen01.png", "boycitizen02.png", "cat.png", "girlcitizen01.png",
            "girlcitizen02.png", "strongman01.png", "human.png", "hero_failure.png", "grandmacitizen01.png"};
    private static final Random rng = new Random();
    //endregion Possible Obstacles

    float lifetime;
    float speed;
    DrivingScene scene;

    public ForkliftObstacle(DrivingScene scene, Vector2 position, float speed, float lifetime) {
        super("Sprites/" + getRandomObstacle(), 1, FrameOrientation.HORIZONTAL, 0.0f);
        this.lifetime = lifetime;
        this.speed = speed;
        this.scene = scene;
        this.position = position;

        setCategoryMask(2);
        setContactMask(1);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifetime -= deltaTime;

        if(lifetime < 0) scene.removeChild(this);

        this.position.setX(position.getX() - (speed * deltaTime));

        if( this.position.getX() < -size.getX()){
            scene.removeObstacle(this, null);
        }
    }

    @Override
    public void onContact(Entity other) {
        scene.removeObstacle(this, other);
    }

    public static String getRandomObstacle(){
        int next = Math.abs(rng.nextInt() % obstacles.length);
        return obstacles[next];
    }
}
