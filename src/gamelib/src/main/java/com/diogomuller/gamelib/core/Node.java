package com.diogomuller.gamelib.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

/**
 * Created by Diogo on 13/10/2014.
 */
public class Node {
    public boolean visible = true;

    private int id;
    private Body body;

    private Paint paint = new Paint();

    protected Vec2 position = new Vec2(0.0f, 0.0f);
    protected Vec2 size = new Vec2(20.0f, 20.0f);
    protected float rotation = 0.0f;

    public Node() {
        this.id = GameActivity.getNextId();
    }

    public void draw(Canvas canvas, float deltaTime) {
        if( !visible ) return;
        canvas.rotate(-rotation);
        paint.setColor(Color.rgb(255, 0, 0));

        canvas.drawRect(position.x - (size.x / 2), position.y - (size.y/2),
                        position.x + (size.x / 2), position.y + (size.y/2),
                        paint);

        canvas.restore();
    }

    public void setPosition(Vec2 position){
        this.position = position;
    }

    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vec2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public int getId() {return id;}
}
