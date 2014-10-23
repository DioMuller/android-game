package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Matrix;

import org.jbox2d.common.Vec2;

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
    public void setPosition(Vec2 position);

    /**
     * Gets Node position.
     * @return Current Node Position.
     */
    public Vec2 getPosition();
    //endregion Position

    //region Size
    /**
     * Sets Node size.
     * @param size New size.
     */
    public void setSize(Vec2 size);

    /**
     * Gets Node size.
     * @return Current Node size.
     */
    public Vec2 getSize();
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
    public void setScale(Vec2 scale);

    /**
     * Gets the node current scale.
     * @return Node current scale.
     */
    public Vec2 getScale();
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
