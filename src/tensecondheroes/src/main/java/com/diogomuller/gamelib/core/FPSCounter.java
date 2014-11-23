package com.diogomuller.gamelib.core;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Diogo on 10/11/2014.
 */
public class FPSCounter {
    Paint paint = new Paint();
    float elapsedTime = 0;
    float calcFPS = 0.0f;
    final float fps = 0.5f;

    public FPSCounter() {
        paint.setTextSize(12);
        paint.setColor(Color.WHITE);
        paint.setFakeBoldText(true);
    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;
        if(elapsedTime >= fps) {
            elapsedTime = 0;
            calcFPS = Math.round(1/deltaTime);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawText("FPS: " + calcFPS, 10, 30, paint);
    }
}
