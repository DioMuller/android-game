package com.diogomuller.gamelib.entities;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.physics.Physics;

/**
 * Created by Diogo on 22/10/2014.
 */
public class PhysicsEntity extends BitmapEntity {

    //region Attributes
    protected Physics physics = null;
    protected Vector2 momentum = Vector2.Zero();
    protected boolean collided = false;
    //endregion Attributes

    public PhysicsEntity(String sprite, int animationCols, FrameOrientation orientation, float milisecondsPerFrame){
        super(sprite, animationCols, orientation, milisecondsPerFrame);
    }

    public PhysicsEntity(int color, float width, float height) {
        super(color, width, height);
    }

    @Override
    public void update(float deltaTime) {
        Vector2 current = this.position;
        updatePhysics(deltaTime);
        super.update(deltaTime);
        //lastPosition = current;
    }

    public void updatePhysics(float deltaTime){
        if( collided ) return;
        if( physics == null ){
            physics = Physics.getInstance();
        }

        momentum = momentum.add(physics.getGravity().multiply(deltaTime));
        position = position.add(momentum.multiply(physics.getPixelsPerMeter()));
    }

    @Override
    public void onCollision(Entity other) {
        super.onCollision(other);
        this.collided = true;
    }

    public void applyForce(Vector2 force){
        this.momentum = Vector2.Zero();
    }
}
