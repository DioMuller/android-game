package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Diogo on 14/10/2014.
 */
public class RectangleNode extends Node {
    private Paint paint = new Paint();

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
