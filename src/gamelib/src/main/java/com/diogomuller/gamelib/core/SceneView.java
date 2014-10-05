package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Diogo on 04/10/2014.
 */
public abstract class SceneView extends View {


    float startTime;
    float elapsedTime;

    public SceneView(Context context) {
        super(context);

        startTime = System.nanoTime();
        elapsedTime = 0;
    }

    @Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();
        elapsedTime += deltaTime;

        update(deltaTime);
        draw(canvas, deltaTime);
    }

    abstract void draw(Canvas canvas, float deltaTime);
    abstract void update(float deltaTime);
}
