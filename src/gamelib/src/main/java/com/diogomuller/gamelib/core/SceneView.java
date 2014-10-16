package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.diogomuller.gamelib.node.Node;
import com.diogomuller.gamelib.physics.BodyDefinition;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import java.util.List;
import java.util.Vector;

/**
 * Created by Diogo on 04/10/2014.
 */
public abstract class SceneView extends View {

    //region Singleton
    private static SceneView instance = null;

    public static SceneView getCurrentInstance() { return instance; }
    //endregion Singleton

    //region Attributes
    private float startTime;
    private float elapsedTime;

    private Node root;
    //endregion Attributes

    //region Physics Attributes
    public int velIterations = 1;
    public int posIterations = 1;

    private int bodyCount = 0;

    private Vec2 gravity = new Vec2(0,0);

    private final Vector<Body> bodyDestroyQueue = new Vector<Body>();
    private final Vector<BodyDefinition> bodyCreateQueue = new Vector<BodyDefinition>();

    public boolean stop = false;
    private boolean running = false;
    private World physicsWorld = null;

    private static float DefaultPPM = 192;
    private static float PPM = 1.0f;
    //endregion Physics Attributes

    //region Constructor
    public SceneView(Context context) {
        super(context);

        // TODO: Parametrize?
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        PPM = Math.max(metrics.widthPixels / 1920.0f, metrics.heightPixels / 1080.0f ) * DefaultPPM;

        instance = this;

        startTime = System.nanoTime();
        elapsedTime = 0;

        physicsWorld = new World(gravity);
        physicsWorld.setAllowSleep(true);

        root = new Node();
        root.setPosition(new Vec2(0,0));
        root.setSize(new Vec2(0,0));

    }
    //endregion Constructor

    //region Android Methods
    @Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();
        elapsedTime += deltaTime;

        updatePhysics(deltaTime);
        update(deltaTime);
        draw(canvas, deltaTime);

        this.invalidate();
    }
    //endregion Android Methods

    //region Node Methods
    public void addNode(Node node){
        root.addChild(node);
    }

    public void removeChild(Node node){
        root.removeChild(node);
    }
    //endregion Node Methods

    //region Game Cycle Methods
    protected void draw(Canvas canvas, float deltaTime) {

    }

    protected void update(float deltaTime) {

    }

    protected void updatePhysics(float deltaTime) {
        // Destroy bodies on Destroy Queue
        if(bodyDestroyQueue.size() > 0) {
            for(Body body: bodyDestroyQueue) {
                physicsWorld.destroyBody(body);
                bodyCount--;
            }

            bodyDestroyQueue.clear();
        }

        // Create bodies on the creation queue
        if(bodyCreateQueue.size() > 0) {
            for(BodyDefinition definition : bodyCreateQueue) {
                definition.getActor().onBodyCreation(physicsWorld.createBody(definition.getDefinition()));
            }
        }

        // Run physics if any bodies exist.
        if(bodyCount > 0) {
            physicsWorld.step(deltaTime/1000.0f, velIterations, posIterations);
        }
    }
    //endregion Game Cycle Methods

    //region Physics Methods
    public void requestBodyCreation(BodyDefinition definition) {
        bodyCreateQueue.add(definition);
        bodyCount++;
    }

    public void destroyBody(Body body) {
        bodyDestroyQueue.add(body);
    }

    public void setGravity(Vec2 gravity) {
        this.gravity = gravity;

        if(physicsWorld != null ) {
            physicsWorld.setGravity(gravity);
        }
    }

    public Vec2 getGravity() {
        return this.gravity;
    }

    public static Vec2 screenToWorld(Vec2 coords) {
        return new Vec2(coords.x / PPM, coords.y / PPM);
    }

    public static Vec2 worldToScreen(Vec2 coords) {
        return new Vec2(coords.x / PPM, coords.y / PPM);
    }

    public static float getPPM() { return PPM; }
    public static float getMPP() { return 1.0f / PPM; }

    //endregion Physics Methods
}
