package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.diogomuller.gamelib.node.Node;

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

    //region Constructor
    public SceneView(Context context) {
        super(context);

        instance = this;

        startTime = System.nanoTime();
        elapsedTime = 0;
    }
    //endregion Constructor

    //region Android Methods
    @Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();
        elapsedTime += deltaTime;

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
    //endregion Game Cycle Methods
}
