package com.diogomuller.gamelib.math;

/**
 * Created by Diogo on 06/11/2014.
 */
public class Vector2 {
    //region Static Vectors
    public static Vector2 Zero() {
        return new Vector2(0, 0);
    }

    public static Vector2 Up() {
        return new Vector2(0, 1);
    }

    public static Vector2 Down() {
        return new Vector2(0, -1);
    }

    public static Vector2 Left() {
        return new Vector2(-1, 0);
    }

    public static Vector2 Right() {
        return new Vector2(1, 0);
    }

    public static Vector2 One() {
        return new Vector2(1, 0);
    }
    //endregion Static Vectors

    //region Static Operations
    public static Vector2 Add(Vector2 v1, Vector2 v2){
        return new Vector2((v1.x + v2.x), (v1.y + v2.y));
    }

    public static Vector2 Subtract(Vector2 v1, Vector2 v2){
        return new Vector2((v1.x - v2.x), (v1.y - v2.y));
    }

    public static Vector2 Multiply(Vector2 v1, float value){
        return new Vector2((v1.x * value), (v1.y * value));
    }

    public static Vector2 Divide(Vector2 v1, float value){
        return new Vector2((v1.x / value), (v1.y / value));
    }
    //endregion Static Operations

    //region Public Attributes
    public float x, y;
    //endregion Public Attibutes

    //region Constructors
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    //endregion Constructors
}
