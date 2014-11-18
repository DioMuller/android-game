package com.diogomuller.gamelib.entities;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.gamelib.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Node class - Base game Entity.
 *
 * Created by Diogo on 13/10/2014.
 */
public abstract class BasicEntity implements Entity {

    //region Attributes
    private int id;

    protected boolean visible = true;
    protected Vector2 position = new Vector2(0.0f, 0.0f);
    protected Vector2 lastPosition = new Vector2();
    protected Vector2 size = new Vector2(20.0f, 20.0f);
    protected float rotation = 0.0f;
    protected Vector2 scale = new Vector2(1.0f, 1.0f);
    protected Rect collisionRect = new Rect();

    private int categoryMask = 0;
    private int collisionMask = 0;
    private int contactMask = 0;
    //endregion Attributes

    //region Constructor
    public BasicEntity() {
        this.id = GameActivity.getNextId();
    }
    //endregion Constructor

    //region Game Cycle Methods
    @Override
    public void update(float deltaTime) {
        this.lastPosition = position;

        Vector2 halfSize = new Vector2((this.size.getX() * scale.getX()) / 2.0f, (this.size.getY() * scale.getY()) / 2.0f);
        Vector2 absPosition = this.getPosition();

        collisionRect.set((int) (absPosition.getX() - halfSize.getX()),
                (int) (absPosition.getY() - halfSize.getY()),
                (int) (absPosition.getX() + halfSize.getX()),
                (int) (absPosition.getY() + halfSize.getY()));
    }

    @Override
    public boolean draw(Canvas canvas, Matrix transformations) {
        if( !visible ) return false;
        return true;
    }
    //endregion Game Cycle Methods

    //region Getters and Setters

    //region Position
    /**
     * Sets Node position.
     * @param position New Position.
     */
    @Override
    public void setPosition(Vector2 position){
        this.position = position;
    }

    /**
     * Gets Node position.
     * @return Current Node Position.
     */
    @Override
    public Vector2 getPosition() {
        return position;
    }
    //endregion Position

    //region Size
    /**
     * Sets Node size.
     * @param size New size.
     */
    @Override
    public void setSize(Vector2 size){
        this.size = size;
    }

    /**
     * Gets Node size.
     * @return Current Node size.
     */
    @Override
    public Vector2 getSize() {
        return size;
    }
    //endregion Size

    //region Rotation
    /**
     * Sets Node rotation.
     * @param rotation New rotation.
     */
    @Override
    public void setRotation(float rotation) {
         this.rotation = rotation;
    }

    /**
     * Gets Node current rotation.
     * @return Node current rotation.
     */
    @Override
    public float getRotation() {
        return rotation;
    }
    //endregion Rotation

    //region Scale
    /**
     * Sets the node scale.
     * @param scale New scale.
     */
    @Override
    public void setScale(Vector2 scale) {this.scale = scale;}

    /**
     * Gets the node current scale.
     * @return Node current scale.
     */
    @Override
    public Vector2 getScale() {return this.scale;}
    //endregion Scale

    //region Id
    @Override
    public int getId() {return id;}
    //endregion Id

    //region Visible
    /**
     * Sets Node visibility.
     * @param visible New visibility.
     */
    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets current visibility.
     * @return Node current visibility.
     */
    @Override
    public boolean getVisible() {
        return visible;
    }
    //endregion Visible

    //endregion Getters and Setters

    //region Collision
    @Override
    public void setCategoryMask(int mask) {
        this.categoryMask = mask;
    }

    @Override
    public int getCategoryMask() {
        return this.categoryMask;
    }

    @Override
    public void setCollisionMask(int mask) {
        this.collisionMask = mask;
    }

    @Override
    public int getCollisionMask() {
        return this.collisionMask;
    }

    @Override
    public void setContactMask(int mask) {
        this.contactMask = mask;
    }

    @Override
    public int getContactMask() {
        return this.contactMask;
    }

    @Override
    public Rect getCollisionRectange() {
        return collisionRect;
    }

    @Override
    public void onCollision(Entity other) {
        if( (other.getCategoryMask() & this.getCollisionMask()) != 0) {
            this.position = lastPosition;
        }
    }

    @Override
    public void onContact(Entity other) {
        // Nothing Else to Do.
    }

    /**
     * Checks if there is contact with other node.
     * @param entity Other node.
     * @return Is there contact with other nodes?
     */
    @Override
    public boolean checkContact(Entity entity){
        if( (entity.getCategoryMask() & this.getContactMask()) != 0 ) {
            if (this.getCollisionRectange().intersect(entity.getCollisionRectange())) {
                onContact(entity);
                entity.onContact(this);
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if there is collision with other node.
     * @param entity Other node.
     * @return Is there collision with other nodes?
     */
    @Override
    public boolean checkCollision(Entity entity){
        if( (entity.getCategoryMask() & this.getCollisionMask()) != 0 ) {
            if (this.getCollisionRectange().intersect(entity.getCollisionRectange())) {
                onCollision(entity);
                entity.onCollision(this);
                return true;
            }
        }

        return false;
    }
    //endregion Collision
}
