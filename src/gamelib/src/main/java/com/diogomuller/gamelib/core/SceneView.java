package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Diogo on 04/10/2014.
 */
public class SceneView extends SurfaceView implements Runnable {

    //region Singleton
    //private static SceneView instance = null;
    //public static SceneView getCurrentScene() { return instance; }
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
    protected Vector2 canvasSize;
    protected float canvasScale;

    private int savedState;

    private GameScene currentScene = null;
    //endregion Attributes

    //region Constructor
    public SceneView(Context context, float desiredHeight) {
        super(context);

        this.context = context;

        holder = this.getHolder();
        paint.setStrokeWidth(1.0f);

        startTime = System.nanoTime();
        elapsedTime = 0;

        DisplayMetrics display = getResources().getDisplayMetrics();
        canvasScale = display.heightPixels / desiredHeight;

        canvasSize = new Vector2(display.widthPixels / canvasScale, display.heightPixels / canvasScale);

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

            if( currentScene == null ) continue;

            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            Canvas canvas = holder.lockCanvas();

            canvas.drawRGB(0, 0, 0);

            if( showFps ) fps.update(deltaTime);
            currentScene.update(deltaTime);

            beginDraw(canvas);
            if( showFps ) fps.draw(canvas);
            currentScene.draw(canvas, matrix);
            endDraw(canvas);

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void beginDraw(Canvas canvas){
        savedState = canvas.save();
        canvas.scale(canvasScale, canvasScale);
    }

    public void endDraw(Canvas canvas){
        canvas.restoreToCount(savedState);
    }

    public void loadScene(GameScene scene){
        this.currentScene = scene;
    }

    public Vector2 getCanvasSize(){
        return canvasSize;
    }

    public float getCanvasScale(){
        return canvasScale;
    }

    public GameScene getCurrentScene(){
        return currentScene;
    }
    //endregion Renderer Methods Methods

    //region Node Methods
}
