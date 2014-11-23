package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.entities.shooter.BombEntity;

import java.util.List;
import java.util.Random;

/**
 * Created by Diogo on 16/11/2014.
 */
public class ShooterScene  extends BaseScene {
    private final float SPAWN_TIME = 1.5f;

    Random rng = new Random();
    float timeSinceLastSpawn = SPAWN_TIME;

    public ShooterScene(Context context, SceneView view) {
        super(context, view);

        AudioController.playMusic("Music/Save Me.mp3");
    }

    @Override
    public void update(float deltaTime) {
        timeSinceLastSpawn += deltaTime;

        if (timeSinceLastSpawn > SPAWN_TIME) {
            timeSinceLastSpawn = 0.0f;
            addObstacle();
        }

        super.update(deltaTime);
    }

    public void addObstacle() {
        BombEntity enemy = new BombEntity(this, new Vector2(50.0f + rng.nextFloat() * (getSize().getX() - 100.0f), getSize().getY()), getSize().getY());
        addChild(enemy);
    }

    @Override
    public void onTouchEntered(List<Vector2> points) {
        for(Entity child : children){
            for(Vector2 point : points){
                int x = (int) (point.getX() / view.getCanvasScale());
                int y = (int) (point.getY() / view.getCanvasScale());
                if(child.getCollisionRectange().contains(x,y) ){
                    removeObstacle(child, false);
                }
            }
        }
    }

    public void removeObstacle(Entity entity, boolean endgame) {
        removeChild(entity);

        if (endgame) {
            AudioController.playSound("Sound/explosion.wav");
            parentActivity.dieAndChangeLevel();
        } else {
            AudioController.playSound("Sound/pickup.wav");
            parentActivity.addScore(1);
        }
    }
}