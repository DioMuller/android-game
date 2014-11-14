package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.diogomuller.gamelib.math.Vector2;

/**
 * Created by Diogo on 19/10/2014.
 */
public interface Node {

    //region Game Cycle Methods
    public void update(float deltaTime);

    public boolean draw(Canvas canvas, Matrix transformations);
    //endregion Game Cycle Methods

    //region Getters and Setters

    //region Position
    /**
     * Sets Node position.
     * @param position New Position.
     */
    public void setPosition(Vector2 position);

    /**
     * Gets Node position.
     * @return Current Node Position.
     */
    public Vector2 getPosition();

    /**
     * Gets the nodes absolute position on the canvas.
     * @return Node absolute position on canvas.
     */
    public Vector2 getAbsolutePosition();
    //endregion Position

    //region Size
    /**
     * Sets Node size.
     * @param size New size.
     */
    public void setSize(Vector2 size);

    /**
     * Gets Node size.
     * @return Current Node size.
     */
    public Vector2 getSize();
    //endregion Size

    //region Rotation
    /**
     * Sets Node rotation.
     * @param rotation New rotation.
     */
    public void setRotation(float rotation);

    /**
     * Gets Node current rotation.
     * @return Node current rotation.
     */
    public float getRotation();
    //endregion Rotation

    //region Scale

    /**
     * Sets the node scale.
     * @param scale New scale.
     */
    public void setScale(Vector2 scale);

    /**
     * Gets the node current scale.
     * @return Node current scale.
     */
    public Vector2 getScale();
    //endregion Scale

    //region Id
    public int getId();
    //endregion Id

    //region Visible
    /**
     * Sets Node visibility.
     * @param visible New visibility.
     */
    public void setVisible(boolean visible);

    /**
     * Gets current visibility.
     * @return Node current visibility.
     */
    public boolean getVisible();
    //endregion Visible

    //endregion Getters and Setters

    //region Node Methods
    public void addChild(Node node);

    public void removeChild(Node node);

    public void setParent(Node parent);
    //endregion Node Methods
}
