package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.diogomuller.gamelib.physics.BodyDefinition;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import java.util.Vector;

/**
 * Created by Diogo on 04/10/2014.
 */
public abstract class SceneView extends View {

    float startTime;
    float elapsedTime;

    //region Physics Attributes
    public static int velIterations = 6;
    public static int posIterations = 6;

    private static int bodyCount = 0;

    private static Vec2 gravity = new Vec2(0,0);

    private static final Vector<Body> bodyDestroyQueue = new Vector<Body>();
    private static final Vector<BodyDefinition> bodyCreateQueue = new Vector<BodyDefinition>();

    public boolean stop = false;
    private boolean running = false;
    private World physicsWorld = null;
    //endregion Physics Attributes

    public SceneView(Context context) {
        super(context);

        startTime = System.nanoTime();
        elapsedTime = 0;

        physicsWorld = new World(gravity);
        physicsWorld.setAllowSleep(true);
    }

    @Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();
        elapsedTime += deltaTime;

        updatePhysics(deltaTime);
        update(deltaTime);
        draw(canvas, deltaTime);
    }

    protected abstract void draw(Canvas canvas, float deltaTime);
    protected abstract void update(float deltaTime);

    protected void updatePhysics(float deltaTime) {
        physicsWorld.step(deltaTime, velIterations, posIterations);
    }
}
