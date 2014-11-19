package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.gamelib.entities.ParallaxEntity;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.entities.space.SpaceHero;
import com.diogomuller.tensecondheroes.entities.space.SpaceShot;

import java.util.List;

/**
 * Created by Diogo on 16/11/2014.
 */
public class SpaceScene extends BaseScene {
    SpaceHero hero;

    public SpaceScene(Context context){
        super(context);

        hero = new SpaceHero(new Vector2(50, 50), this);
        this.addChild(hero);

        ParallaxEntity background = new ParallaxEntity("Images/background_nightsky.png", getSize(), 100.0f );
        this.addChild(background);
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

        hero.moveToY(nearest.getY() / canvasScale);
    }

    @Override
    public void onTouchExit(List<Vector2> points) {
        hero.activate(false);
    }

    public void addShoot(Vector2 position){
        SpaceShot shot = new SpaceShot(this, 100.0f, 3.0f);
        shot.setPosition(position);
        addChild(shot);
    }
}
