package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.jbox2d.common.Vec2;

/**
 * Created by Diogo on 14/10/2014.
 */
public class RectangleNode extends Node {
    private Paint paint = new Paint();

    public RectangleNode(float width, float height) {
        super();
        this.setSize(new Vec2(width, height));
        createPhysicsBody(1.0f, 1.0f, 1.0f);
    }

    @Override
    public void draw(Canvas canvas, float deltaTime) {
        canvas.rotate(-getRotation());
        paint.setColor(Color.rgb(255, 0, 0));

        canvas.drawRect(getPosition().x - (getSize().x / 2), getPosition().y - (getSize().y/2),
                getPosition().x + (getSize().x / 2), getPosition().y + (getSize().y/2),
                paint);

        canvas.restore();
    }
}
