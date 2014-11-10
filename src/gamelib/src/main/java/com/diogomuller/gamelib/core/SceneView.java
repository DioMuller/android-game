package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.Node;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Diogo on 04/10/2014.
 */
public class SceneView extends SurfaceView implements Runnable, Node {

    //region Singleton
    private static SceneView instance = null;
    public static SceneView getCurrentInstance() { return instance; }
    //endregion Singleton

    //region Attributes
    private float startTime;
    private float elapsedTime;
    protected Context context;
    private Matrix matrix = new Matrix();
    private FPSCounter fps;
    protected boolean showFps = false;
    protected Random rng;
    Thread process = null;
    Paint paint = new Paint();
    SurfaceHolder holder;
    boolean running = false;

    private List<Node> children;
    //endregion Attributes

    //region Constructor
    public SceneView(Context context) {
        super(context);

        this.context = context;
        instance = this;

        holder = this.getHolder();
        paint.setStrokeWidth(1.0f);

        children = new ArrayList<Node>();

        startTime = System.nanoTime();
        elapsedTime = 0;

        fps = new FPSCounter();
        rng = new Random();
    }
    //endregion Constructor

    //region Renderer Methods
    public void resume() {
        running = true;
        process = new Thread(this);
        process.start();
    }

    public void pause() {
        running = false;
        while(true) {
            try {
                process.join();
                break;
            } catch( Exception e ){
                // Waiting for the thread to die.
            }
        }
    }

    @Override
    public void run() {
        while(running) {
            if( !holder.getSurface().isValid() ) continue;

            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            Canvas canvas = holder.lockCanvas();
            canvas.drawRGB(0, 0, 0);

            for(int i = 0; i < 1000; i++ ) {
                paint.setColor(Color.rgb(rng.nextInt(256),
                        rng.nextInt(256), rng.nextInt(256)));

                canvas.drawPoint(rng.nextInt(canvas.getWidth()),
                        rng.nextInt(canvas.getHeight()), paint);
            }

            fps.update(deltaTime);
            fps.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }
    //endregion Renderer Methods Methods

    //region Node Methods

    //region Game Cycle
    @Override
    public void update(float deltaTime) {
        if( showFps ) fps.update(deltaTime);
        for(Node child : children) {
            child.update(deltaTime);
        }
    }

    @Override
    public boolean draw(Canvas canvas, Matrix matrix){
        if( showFps ) fps.draw(canvas);
        for(Node child : children) {
            child.draw(canvas, matrix);
        }

        return true;
    }
    //endregion Game Cycle

    //region Getters and Setters
    @Override
    public void setPosition(Vector2 position) {
        // Nothing else to do.
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(0,0);
    }

    @Override
    public void setSize(Vector2 size) {
        // Nothing else to do.
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(0,0);
    }

    @Override
    public void setRotation(float rotation) {
        // Nothing else to do.
    }

    @Override
    public float getRotation() {
        return 0;
    }

    @Override
    public void setScale(Vector2 scale) {
        // Nothing else to do.
    }

    @Override
    public Vector2 getScale() {
        return new Vector2(1,1);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setVisible(boolean visible) {
        // Nothing else to do.
    }

    @Override
    public boolean getVisible() {
        return true;
    }

    //region Node Methods
    @Override
    public void addChild(Node node) {
        children.add(node);
        node.setParent(this);
    }

    @Override
    public void removeChild(Node node) {
        children.remove(node);
    }

    @Override
    public void setParent(Node parent) {
        // Nothing else to do.
    }
    //endregion Node Methods

    //endregion Node Methods

    //endregion Node Methods
}
