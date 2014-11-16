package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.Node;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Diogo on 04/10/2014.
 */
public class SceneView extends SurfaceView implements Runnable, Node {

    //region Singleton
    private static SceneView instance = null;
    public static SceneView getCurrentScene() { return instance; }
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
    protected Stack<Node> toRemove = new Stack<Node>();
    protected Stack<Node> toAdd = new Stack<Node>();

    private List<Node> children;
    //endregion Attributes

    //region Constructor
    public SceneView(Context context, float desiredHeight) {
        super(context);

        this.context = context;
        instance = this;

        holder = this.getHolder();
        paint.setStrokeWidth(1.0f);

        children = new ArrayList<Node>();

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

            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            Canvas canvas = holder.lockCanvas();
            canvas.drawRGB(0, 0, 0);

            while(!toRemove.isEmpty()) {
                children.remove(toRemove.pop());
            }

            while(!toAdd.isEmpty()) {
                children.add(toAdd.pop());
            }

            this.update(deltaTime);
            this.draw(canvas, matrix);

            holder.unlockCanvasAndPost(canvas);
        }
    }
    //endregion Renderer Methods Methods

    //region Node Methods

    //region Game Cycle
    @Override
    public void update(float deltaTime) {
        if( showFps ) fps.update(deltaTime);

        for(int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            child.update(deltaTime);

            if( child.getCategoryMask() != 0 ) {
                for(int j = i + 1; j < children.size(); j++) {
                    Node other = children.get(j);
                    child.checkCollision(other);
                    child.checkContact(other);
                }
            }
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
        return Vector2.Zero();
    }

    @Override
    public Vector2 getAbsolutePosition() {
        return Vector2.Zero();
    }

    /**
     * Sets canvas Size, by height. Width will always be calculated.
     * @param size New size.
     */
    @Override
    public void setSize(Vector2 size) {
        float desiredHeight = size.getY();
        DisplayMetrics display = getResources().getDisplayMetrics();
        canvasScale = display.heightPixels / desiredHeight;

        canvasSize = new Vector2(display.widthPixels / canvasScale, display.heightPixels / canvasScale);
    }

    @Override
    public Vector2 getSize() {
        return canvasSize;
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

    public Vector2 getCanvasSize(){
        return canvasSize;
    }

    public float getCanvasScale(){
        return canvasScale;
    }
    //endregion Getters and Setters

    //region Node Methods
    @Override
    public void addChild(Node node) {
        if(children.contains(node) || toAdd.contains(node)) return;

        toAdd.add(node);
        node.setParent(this);
    }

    @Override
    public void removeChild(Node node) {
        toRemove.push(node);
    }

    @Override
    public void setParent(Node parent) {
        // Nothing else to do.
    }
    //endregion Node Methods

    //region Collision
    /**
     * Sets the node category mask.
     *
     * @param mask New collision category mask.
     */
    @Override
    public void setCategoryMask(int mask) {
        // Nothing Else To Do
    }

    /**
     * Gets the node category mask.
     *
     * @return Collision category mask.
     */
    @Override
    public int getCategoryMask() {
        return 0;
    }

    /**
     * Sets the node collision mask. This will make the node collide only with nodes of this category.
     *
     * @param mask New Collision mask.
     */
    @Override
    public void setCollisionMask(int mask) {
        // Nothing Else To Do
    }

    /**
     * Gets the node collision mask. This will make the node collide only with nodes of this category.
     *
     * @return Node Collision Mask.
     */
    @Override
    public int getCollisionMask() {
        return 0;
    }

    /**
     * Sets the node contact mask. If a node with an id on the contact mask intersects with this, the onContact event will be fired.
     *
     * @param mask New contact mask.
     */
    @Override
    public void setContactMask(int mask) {
        // Nothing Else To Do
    }

    /**
     * Gets the node contact mask. If a node with an id on the contact mask intersects with this, the onContact event will be fired.
     *
     * @return Node contact mask.
     */
    @Override
    public int getContactMask() {
        return 0;
    }

    /**
     * Obtains the node collision rectangle.
     *
     * @return Node collision rectangle.
     */
    @Override
    public Rect getCollisionRectange() {
        return null;
    }

    /**
     * Event Called on Collision. Should be used only if the node works differently on collision.
     *
     * @param other Other body.
     */
    @Override
    public void onCollision(Node other) {
        // Nothing Else To Do
    }

    /**
     * Event called on contact. Will be called when there is contact, but no collision.
     *
     * @param other Other body.
     */
    @Override
    public void onContact(Node other) {
        // Nothing Else To Do
    }

    /**
     * Checks if there is contact with other node.
     *
     * @param node Other node.
     * @return Is there contact with other nodes?
     */
    @Override
    public boolean checkContact(Node node) {
        return false;
    }

    /**
     * Checks if there is collision with other node.
     *
     * @param node Other node.
     * @return Is there collision with other nodes?
     */
    @Override
    public boolean checkCollision(Node node) {
        return false;
    }
    //endregion Collision

    //region Touch
    public void onTouchEntered(List<Vector2> points){
        for( Vector2 point : points ) {
            Log.d("Touch - Enter", point.toString());
        }
    }

    public void onTouchMoved(List<Vector2> points){
        for( Vector2 point : points ) {
            Log.d("Touch - Moved", point.toString());
        }
    }

    public void onTouchExit(List<Vector2> points){
        for( Vector2 point : points ) {
            Log.d("Touch - Exit", point.toString());
        }
    }
    //endregion Touch
}
