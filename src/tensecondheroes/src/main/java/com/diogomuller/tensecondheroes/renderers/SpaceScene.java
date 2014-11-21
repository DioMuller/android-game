package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.transition.Scene;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.entities.ParallaxEntity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.entities.space.SpaceEnemy;
import com.diogomuller.tensecondheroes.entities.space.SpaceHero;
import com.diogomuller.tensecondheroes.entities.space.SpaceShot;

import java.util.List;
import java.util.Random;

/**
 * Created by Diogo on 16/11/2014.
 */
public class SpaceScene extends BaseScene {
    private final float SPAWN_TIME = 1.0f;

    SpaceHero hero;
    Random rng = new Random();
    float timeSinceLastSpawn = 0.0f;

    public SpaceScene(Context context, SceneView view){
        super(context, view);

        hero = new SpaceHero(this, new Vector2(50, 50));
        this.addChild(hero);

        ParallaxEntity background = new ParallaxEntity("Images/background_nightsky.png", getSize(), 100.0f );
        this.addChild(background);

        AudioController.playMusic("Music/Save Me.ogg");
    }

    @Override
    public void update(float deltaTime) {
        timeSinceLastSpawn += deltaTime;

        if( timeSinceLastSpawn > SPAWN_TIME ){
            timeSinceLastSpawn = 0.0f;
            addEnemy();
        }

        super.update(deltaTime);
    }

    @Override
    public void onTouchEntered(List<Vector2> points) {
        hero.activate(true);
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

    @Override
    public void onTouchExit(List<Vector2> points) {
        hero.activate(false);
    }

    public void addShoot(Vector2 position){
        SpaceShot shot = new SpaceShot(this, 150.0f, 4.0f);
        shot.setPosition((Vector2) position.clone());
        AudioController.playSound("Sound/drop.wav");
        addChild(shot);
    }

    public void addEnemy(){
        SpaceEnemy enemy = new SpaceEnemy(this, new Vector2(getSize().getX(), 50.0f + rng.nextFloat() * 220.0f ), 100.0f, 10.0f);
        addChild(enemy);
    }

    public void removeEnemy(Entity enemy, Entity collider){
        removeChild(enemy);
        removeChild(collider);

        AudioController.playSound("Sound/explosion.wav");

        if( collider == hero ){
            parentActivity.dieAndChangeLevel();
        } else {
            parentActivity.addScore(1);
        }
    }
}
