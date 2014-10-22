package com.diogomuller.gamelib.node;

import android.graphics.Canvas;

import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.gamelib.math.Vector2;

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
    private Vector2 position = new Vector2(0.0f, 0.0f);
    private Vector2 size = new Vector2(20.0f, 20.0f);
    private float rotation = 0.0f;
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
    public boolean draw(Canvas canvas) {
        if( !visible ) return false;

        for(Node child : children) {
            child.draw(canvas);
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