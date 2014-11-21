package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Color;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.BitmapEntity;
import com.diogomuller.gamelib.entities.ParallaxEntity;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.entities.flappy.FlappyHero;
import com.diogomuller.tensecondheroes.entities.flappy.FlappyObstacle;

import java.util.List;
import java.util.Random;

/**
 * Created by Diogo on 16/11/2014.
 */
public class FlappyScene extends BaseScene {
    private final float SPAWN_TIME = 3.0f;

    private FlappyHero hero;
    private static Random rng = new Random();
    float timeSinceLastSpawn = 2.0f;

    public FlappyScene(Context context, SceneView view) {
        super(context, view);

        hero = new FlappyHero(this, new Vector2(50, 50));

        this.addChild(hero);

        BitmapEntity ground = new BitmapEntity(Color.argb(255, 0, 255, 128), getSize().getX(), 60);
        ground.setPosition(new Vector2(getSize().getX() / 2 , getSize().getY() - 15));
        ground.setCategoryMask(2);
        ground.setContactMask(5);
        this.addChild(ground);

        ParallaxEntity background = new ParallaxEntity("Images/background_morningsky.png", getSize(), 100.0f );
        this.addChild(background);

        AudioController.playMusic("Music/We Don't Need a Hero.ogg");
    }

    @Override
    public void onTouchEntered(List<Vector2> points) {
        hero.impulse();
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

    public void endScene(){
        parentActivity.dieAndChangeLevel();
    }

    public void addObstacle(){
        FlappyObstacle enemy = new FlappyObstacle(this, new Vector2(getSize().getX(), 50.0f + rng.nextFloat() * 220.0f ), 200.0f, 10.0f);
        addChild(enemy);
    }

    public void removeObstacle(Entity enemy, Entity collider){
        removeChild(enemy);
        if( collider != null ) removeChild(collider);



        if( collider == hero ){
            AudioController.playSound("Sound/explosion.wav");
            parentActivity.dieAndChangeLevel();
        }
    }

    public void addScore(){
        AudioController.playSound("Sound/pickup.wav");
        parentActivity.addScore(1);
    }
}
