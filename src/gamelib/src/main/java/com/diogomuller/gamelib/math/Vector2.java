package com.diogomuller.gamelib.math;

import java.util.Vector;

/**
 * Created by Diogo on 06/11/2014.
 */
public class Vector2 implements Cloneable {

    //region Static Vectors

    /**
     * Zero vector.
     * @return (0,0)
     */
    public static Vector2 Zero() {
        return new Vector2(0, 0);
    }

    /**
     * Up vector.
     * @return (0,1)
     */
    public static Vector2 Up() {
        return new Vector2(0, 1);
    }

    /**
     * Down vector.
     * @return (0,-1)
     */
    public static Vector2 Down() {
        return new Vector2(0, -1);
    }

    /**
     * Left vector.
     * @return (-1,0)
     */
    public static Vector2 Left() {
        return new Vector2(-1, 0);
    }

    /**
     * Right vector.
     * @return (1,0)
     */
    public static Vector2 Right() {
        return new Vector2(1, 0);
    }

    /**
     * One vector.
     * @return (1,1)
     */
    public static Vector2 One() {
        return new Vector2(1, 0);
    }
    //endregion Static Vectors

    //region Static Operations
    /**
     * Adds two vectors.
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return v1 + v2.
     */
    public static Vector2 add(Vector2 v1, Vector2 v2){
        return new Vector2((v1.x + v2.x), (v1.y + v2.y));
    }

    /**
     * Subtracts one vector from another.
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return v1 - v2.
     */
    public static Vector2 subtract(Vector2 v1, Vector2 v2){
        return new Vector2((v1.x - v2.x), (v1.y - v2.y));
    }

    /**
     * Multiply a vector by a value.
     * @param v1 Vector.
     * @param value Value.
     * @return v1 * value.
     */
    public static Vector2 multiply(Vector2 v1, float value){
        return new Vector2((v1.x * value), (v1.y * value));
    }

    /**
     * Divides a vector by a value.
     * @param v1 Vector.
     * @param value Value.
     * @return v1 / value.
     */
    public static Vector2 divide(Vector2 v1, float value){
        return new Vector2((v1.x / value), (v1.y / value));
    }

    /**
     * Compares two vectors.
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return Is v1 equal to v2?
     */
    public static boolean equals(Vector2 v1, Vector2 v2){
        return v1.x == v2.x && v1.y == v2.y;
    }

    /**
     * Dot product between two vectors.
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return v1 . v2.
     */
    public static float dot(Vector2 v1, Vector2 v2){
        return v1.x * v2.x + v1.y * v2.y;
    }

    /**
     * Squared distance between two vectors.
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return Square Distance between v1 and v2.
     */
    public static float squareDistance(Vector2 v1, Vector2 v2){
        return ((v1.getX() - v2.getX()) * (v1.getX() - v2.getX())) + ((v1.getY() - v2.getY()) * (v1.getY() - v2.getY()));
    }

    /**
     * Euclydean distance between two vectors.
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return Euclydean Distance between v1 and v2.
     */
    public static float distance(Vector2 v1, Vector2 v2){
        return (float) Math.sqrt(squareDistance(v1, v2));
    }
    //endregion Static Operations

    //region Attributes
    private float x;
    private float y;
    //endregion Attibutes

    //region Getters and Setters

    /**
     * Gets the vector X value.
     * @return X.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the vector X value.
     * @param x New x value.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the vector Y value.
     * @return Y.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the vector Y value.
     * @param y New Y.
     */
    public void setY(float y) {
        this.y = y;
    }
    //endregion

    //region Constructors
    /**
     * Basic Constructor. Initializes with (0,0).
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * Basic constructor.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    //endregion Constructors

    //region Vector Properties
    /**
     * Calculates vector square size.
     * @return Vector square size.
     */
    public float getSquareSize(){
        return x * x + y * y;
    }

    /**
     * Calculates vector size. Costly, if possible use getSquareSize.
     * @return Vector size.
     */
    public float getSize(){
        return (float) Math.sqrt((double) getSquareSize());
    }

    /**
     * Returns Vector Angle.
     * @return Vector Angle.
     */
    public float getAngle(){
        return (float) Math.atan2((double) y,(double)  x);
    }
    //endregion Vector Properties

    //region Vector Operations
    /**
     * Addition operation.
     * @param other Vector to be added to this.
     * @return This + Other
     */
    public Vector2 add(Vector2 other){
        return Vector2.add(this, other);
    }

    /**
     * Subtraction operation.
     * @param other Vector to be subtracted.
     * @return This - Other.
     */
    public Vector2 subtract(Vector2 other){
        return Vector2.subtract(this, other);
    }

    /**
     * Multipies vector by a value.
     * @param value Value to be multiplied.
     * @return This * Value
     */
    public Vector2 multiply(float value){
        return Vector2.multiply(this, value);
    }

    /**
     * Divides vector by a value.
     * @param value Value the vector will be divided by.
     * @return This / Value
     */
    public Vector2 divide(float value){
        return Vector2.divide(this, value);
    }

    /**
     * Dot product between vectors.
     * @param other Other vector to calculate the dot product.
     * @return v1 . v2
     */
    public float dot(Vector2 other) {
        return Vector2.dot(this, other);
    }

    /**
     * Normalizes vector.
     * @return A normalized vector.
     */
    public Vector2 normalize(){
        return Vector2.divide(this, getSize());
    }

    /**
     * Square distance between this and another Vector.
     * @param other Other vector.
     * @return Square distance between this and the other vector.
     */
    public float squareDistance(Vector2 other){
        return Vector2.squareDistance(this, other);
    }

    /**
     * Euclydean distance between this and another Vector.
     * @param other Other vector.
     * @return Euclydean distance between this and the other vector.
     */
    public float distance(Vector2 other){
        return Vector2.distance(this, other);
    }
    //endregion Vector Operations

    //region Helper

    /**
     * Creates a copy of this vector.
     * @return Vector Copy.
     */
    @Override
    public Object clone(){
        return new Vector2(x, y);
    }

    /**
     * Compare this instance to another.
     * @param obj Object to be compared with.
     * @return Are the objects equal?
     */
    @Override
    public boolean equals(Object obj){
        Vector2 other = (Vector2) obj;

        if( other == null ) return false;

        return Vector2.equals(this, other);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder = builder.append("[ ").append(x).append(" , ").append(y).append(" ]");
        return builder.toString();
    }

    //endregion Helper
}
