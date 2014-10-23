package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

import com.diogomuller.gamelib.node.Node;

import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diogo on 04/10/2014.
 */
public class SceneView extends View implements Node {

    //region Singleton
    private static SceneView instance = null;

    public static SceneView getCurrentInstance() { return instance; }
    //endregion Singleton

    //region Attributes
    private float startTime;
    private float elapsedTime;
    protected Context context;
    private Matrix matrix = new Matrix();

    private List<Node> children;
    //endregion Attributes

    //region Constructor
    public SceneView(Context context) {
        super(context);

        this.context = context;

        instance = this;

        children = new ArrayList<Node>();

        startTime = System.nanoTime();
        elapsedTime = 0;
    }
    //endregion Constructor

    //region Android Methods
    //@Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();
        elapsedTime += deltaTime;

        update(deltaTime);
        draw(canvas, matrix);

        this.invalidate();
    }
    //endregion Android Methods

    //region Node Methods

    //region Game Cycle
    @Override
    public void update(float deltaTime) {
        for(Node child : children) {
            child.update(deltaTime);
        }
    }

    @Override
    public boolean draw(Canvas canvas, Matrix matrix){
        for(Node child : children) {
            child.draw(canvas, matrix);
        }

        return true;
    }
    //endregion Game Cycle

    //region Getters and Setters
    @Override
    public void setPosition(Vec2 position) {
        // Nothing else to do.
    }

    @Override
    public Vec2 getPosition() {
        return new Vec2(0,0);
    }

    @Override
    public void setSize(Vec2 size) {
        // Nothing else to do.
    }

    @Override
    public Vec2 getSize() {
        return new Vec2(0,0);
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
    public void setScale(Vec2 scale) {
        // Nothing else to do.
    }

    @Override
    public Vec2 getScale() {
        return new Vec2(1,1);
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
}
