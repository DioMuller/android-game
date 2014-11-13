package com.diogomuller.gamelib.physics;

import com.diogomuller.gamelib.math.Vector2;

/**
 * Created by Diogo on 12/11/2014.
 */
public class Physics {

    //region Singleton
    private static final Physics instance = new Physics();

    public static Physics getInstance() {
        return instance;
    }
    //endregion Singleton

    //region Attributes
    private Vector2 gravity;
    private float pixelsPerMeter;
    //endregion Attributes

    //region Constructor
    private Physics(){
        gravity = Vector2.Zero();
        pixelsPerMeter = 1.0f;
    }
    //endregion Constructor

    //region Getters and Setters
    public Vector2 getGravity() {
        return gravity;
    }

    public void setGravity(Vector2 gravity) {
        this.gravity = gravity;
    }

    public float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    public void setPixelsPerMeter(float pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
    }
    //endregion Getters and Setters

}
