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

    public RectangleNode(Node parent, float width, float height) {
        super(parent);
        this.size = new Vec2(width, height);
    }

    @Override
    public void draw(Canvas canvas, float deltaTime) {
        canvas.rotate(-rotation);
        paint.setColor(Color.rgb(255, 0, 0));

        canvas.drawRect(position.x - (size.x / 2), position.y - (size.y/2),
                position.x + (size.x / 2), position.y + (size.y/2),
                paint);

        canvas.restore();
    }
}
