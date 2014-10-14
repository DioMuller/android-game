package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.diogomuller.gamelib.core.GameActivity;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class - Base game Entity.
 *
 * Created by Diogo on 13/10/2014.
 */
public class Node {

    //region Attributes
    private int id;
    private Body body;

    private Node parent;
    private List<Node> children;

    protected boolean visible = true;
    protected Vec2 position = new Vec2(0.0f, 0.0f);
    protected Vec2 size = new Vec2(20.0f, 20.0f);
    protected float rotation = 0.0f;
    //endregion Attributes

    //region Constructor
    public Node(Node parent) {
        this.parent = parent;
        parent.addChild(this);
        this.children = new ArrayList<Node>();

        this.id = GameActivity.getNextId();
    }
    //endregion Constructor

    //region Game Cycle Methods
    public void update(float deltaTime) {

    }

    public void draw(Canvas canvas, float deltaTime) {
        if( !visible ) return;
    }
    //endregion Game Cycle Methods

    //region Getters and Setters

    //region Position
    /**
     * Sets Node position.
     * @param position New Position.
     */
    public void setPosition(Vec2 position){
        this.position = position;
    }

    /**
     * Gets Node position.
     * @return Current Node Position.
     */
    public Vec2 getPosition() {
        return position;
    }
    //endregion Position

    //region Size
    /**
     * Sets Node size.
     * @param size New size.
     */
    public void setSize(Vec2 size){
        this.size = size;
    }

    /**
     * Gets Node size.
     * @return Current Node size.
     */
    public Vec2 getSize() {
        return size;
    }
    //endregion Size

    //region Rotation
    /**
     * Sets Node rotation.
     * @param rotation New rotation.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Gets Node current rotation.
     * @return Node current rotation.
     */
    public float getRotation() {
        return rotation;
    }
    //endregion Rotation

    //region Id
    public int getId() {return id;}
    //endregion Id

    //region Visible
    /**
     * Sets Node visibility.
     * @param visible New visibility.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets current visibility.
     * @return Node current visibility.
     */
    public boolean getVisible() {
        return visible;
    }
    //endregion Visible

    //endregion Getters and Setters

    //region Node Methods
    public void addChild(Node node){
        if(!children.contains(node)) children.add(node);
    }

    public void removeChild(Node node){
        children.remove(node);
    }
    //endregion Node Methods
}
