package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.diogomuller.gamelib.core.GameActivity;

import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class - Base game Entity.
 *
 * Created by Diogo on 13/10/2014.
 */
public abstract class BasicNode implements Node {

    //region Attributes
    private int id;

    private Node parent;
    private List<Node> children;

    private boolean visible = true;
    protected Vec2 position = new Vec2(0.0f, 0.0f);
    protected Vec2 size = new Vec2(20.0f, 20.0f);
    protected float rotation = 0.0f;
    protected Vec2 scale = new Vec2(0.0f, 0.0f);
    //endregion Attributes

    //region Constructor
    public BasicNode() {
        this.children = new ArrayList<Node>();

        this.id = GameActivity.getNextId();
    }
    //endregion Constructor

    //region Game Cycle Methods
    @Override
    public void update(float deltaTime) {
        for(Node child : children) {
            child.update(deltaTime);
        }
    }

    @Override
    public boolean draw(Canvas canvas, Matrix transformations) {
        if( !visible ) return false;

        for(Node child : children) {
            child.draw(canvas, transformations);
        }

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
    public void setPosition(Vec2 position){
        this.position = position;
    }

    /**
     * Gets Node position.
     * @return Current Node Position.
     */
    @Override
    public Vec2 getPosition() {
        return position;
    }
    //endregion Position

    //region Size
    /**
     * Sets Node size.
     * @param size New size.
     */
    @Override
    public void setSize(Vec2 size){
        this.size = size;
    }

    /**
     * Gets Node size.
     * @return Current Node size.
     */
    @Override
    public Vec2 getSize() {
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
    public void setScale(Vec2 scale) {this.scale = scale;}

    /**
     * Gets the node current scale.
     * @return Node current scale.
     */
    @Override
    public Vec2 getScale() {return this.scale;}
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

    //region Node Methods
    @Override
    public void addChild(Node node){
        if(!children.contains(node)) children.add(node);
    }

    @Override
    public void removeChild(Node node){
        children.remove(node);
        node.setParent(null);
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
        if(parent != null) parent.addChild(this);
    }
    //endregion Node Methods
}
