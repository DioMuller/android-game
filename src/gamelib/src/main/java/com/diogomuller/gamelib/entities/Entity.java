package com.diogomuller.gamelib.entities;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.diogomuller.gamelib.math.Vector2;

/**
 * Created by Diogo on 19/10/2014.
 */
public interface Entity {

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

    /**
     * Gets node Id.
     * @return Node id.
     */
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

    //region Collision Methods

    //region Category Mask
    /**
     * Sets the node category mask.
     * @param mask New collision category mask.
     */
    public void setCategoryMask(int mask);
    /**
     * Gets the node category mask.
     * @return Collision category mask.
     */
    public int getCategoryMask();
    //endregion Category Mask

    //region Contact Mask
    /**
     * Sets the node contact mask. If a node with an id on the contact mask intersects with this, the onContact event will be fired.
     * @param mask New contact mask.
     */
    public void setContactMask(int mask);

    /**
     * Gets the node contact mask. If a node with an id on the contact mask intersects with this, the onContact event will be fired.
     * @return Node contact mask.
     */
    public int getContactMask();
    //endregion Contact Mask

    //region Collision Rect
    /**
     * Obtains the node collision rectangle.
     * @return Node collision rectangle.
     */
    public Rect getCollisionRectange();
    //endregion Collision Rect

    //region Events
    /**
     * Event called on contact. Will be called when there is contact, but no collision.
     * @param other Other body.
     */
    public void onContact(Entity other);

    /**
     * Checks if there is contact with other node.
     * @param entity Other node.
     * @return Is there contact with other nodes?
     */
    public boolean checkContact(Entity entity);
    //endregion Events

    //endregion Collision Methods
}
