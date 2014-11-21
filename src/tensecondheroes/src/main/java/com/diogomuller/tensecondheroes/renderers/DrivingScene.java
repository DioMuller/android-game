package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.entities.ParallaxEntity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.entities.forklift.ForkliftHero;
import com.diogomuller.tensecondheroes.entities.forklift.ForkliftObstacle;

import java.util.List;
import java.util.Random;

/**
 * Created by Diogo on 16/11/2014.
 */
public class DrivingScene extends BaseScene {
    private final float SPAWN_TIME = 1.5f;

    ForkliftHero hero;
    Random rng = new Random();
    float timeSinceLastSpawn = 0.0f;

    public DrivingScene(Context context, SceneView view){
        super(context, view);

        hero = new ForkliftHero(this, new Vector2(50, 50));
        this.addChild(hero);

        ParallaxEntity background = new ParallaxEntity("Images/background_cement.png", getSize(), 100.0f );
        this.addChild(background);

        AudioController.playMusic("Music/Save Me.ogg");
    }

    @Override
    public void update(float deltaTime) {
        timeSinceLastSpawn += deltaTime;

        if( timeSinceLastSpawn > SPAWN_TIME ){
            timeSinceLastSpawn = 0.0f;
            addObstacle();
        }

        super.update(deltaTime);
    }

    @Override
    public void onTouchMoved(List<Vector2> points) {
        Vector2 nearest = points.get(0);
        Vector2 heroPos = hero.getPosition();
        float nearestDistance = heroPos.squareDistance(nearest);

        for(Vector2 point : points){
            float dist = heroPos.squareDistance(point);
            if( dist < nearestDistance){
                nearest = point;
                nearestDistance = dist;
            }
        }

        hero.moveToY(nearest.getY() / view.getCanvasScale());
    }

    public void addObstacle(){
        ForkliftObstacle enemy = new ForkliftObstacle(this, new Vector2(getSize().getX(), 50.0f + rng.nextFloat() * 220.0f ), 100.0f, 10.0f);
        addChild(enemy);
    }

    public void removeObstacle(Entity enemy, Entity collider){
        removeChild(enemy);
        if( collider != null ) removeChild(collider);



        if( collider == hero ){
            AudioController.playSound("Sound/explosion.wav");
            parentActivity.dieAndChangeLevel();
        } else {
            AudioController.playSound("Sound/pickup.wav");
            parentActivity.addScore(1);
        }
    }
}