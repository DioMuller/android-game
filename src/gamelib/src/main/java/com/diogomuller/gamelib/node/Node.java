package com.diogomuller.gamelib.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.transition.Scene;

import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.physics.BodyDefinition;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

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

    private boolean visible = true;
    private Vec2 position = new Vec2(0.0f, 0.0f);
    private Vec2 size = new Vec2(20.0f, 20.0f);
    private float rotation = 0.0f;

    protected float friction;
    protected float density;
    protected float restitution;
    //endregion Attributes

    //region Constructor
    public Node() {
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
        if( body == null ) {
            this.position = position;
        } else {
            body.setTransform(position, body.getAngle());
        }
    }

    /**
     * Gets Node position.
     * @return Current Node Position.
     */
    public Vec2 getPosition() {
        if( body == null ) {
            return SceneView.worldToScreen(position);
        } else {
            return SceneView.worldToScreen(body.getPosition());
        }
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
        return SceneView.worldToScreen(size);
    }
    //endregion Size

    //region Rotation
    /**
     * Sets Node rotation.
     * @param rotation New rotation.
     */
    public void setRotation(float rotation) {
        if( body == null ) {
            this.rotation = rotation;
        } else {
            body.setTransform(body.getPosition(), rotation * 0.0174532925f);
        }
    }

    /**
     * Gets Node current rotation.
     * @return Node current rotation.
     */
    public float getRotation() {
        if( body == null ) {
            return rotation;
        } else {
            return body.getAngle() * 57.2957795786f;
        }
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
        node.setParent(null);
    }

    public void setParent(Node parent) {
        this.parent = parent;
        if(parent != null) parent.addChild(this);
    }
    //endregion Node Methods

    //region Physics Methods
    public void createPhysicsBody(float density, float friction, float restitution) {
        // Do not create another body if one already exists.
        if( body != null ) return;

        // Save the values
        this.friction = friction;
        this.density = density;
        this.restitution = restitution;

        // Create Body
        BodyDef definition = new BodyDef();

        if(density > 0) {
            definition.type = BodyType.DYNAMIC;
        } else {
            definition.type = BodyType.STATIC;
        }

        definition.position = SceneView.screenToWorld(this.position);

        SceneView.getCurrentInstance().requestBodyCreation(new BodyDefinition(this, definition));
    }

    public void onBodyCreation(Body body) {
        this.body = body;

        //TODO: Non-fixed forms.
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x, size.y, position, rotation * 0.0174532925f);
        //END TODO: Non-fixed forms

        // Attach Fixture
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = density;
        fixture.friction = friction;
        fixture.restitution = restitution;
        // Resets position and rotation to the ones before creating the body.
        setPosition(position);
        setRotation(rotation);

        this.body.createFixture(fixture);
    }

    public void destroyPhysicsBody() {
        if(body == null) return;

        Body toDestroy = body;
        body = null;

        setPosition(toDestroy.getPosition());
        setRotation(toDestroy.getAngle() * 57.2957795786f);

        SceneView.getCurrentInstance().destroyBody(toDestroy);
    }
    //endregion Physics Methods
}
