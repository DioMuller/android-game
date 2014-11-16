package com.diogomuller.gamelib.node;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.physics.Physics;

/**
 * Created by Diogo on 22/10/2014.
 */
public class PhysicsNode extends BitmapNode {

    //region Attributes
    protected Physics physics = null;
    protected Vector2 momentum = Vector2.Zero();
    protected Vector2 acceleration = Vector2.Zero();
    //endregion Attributes

    public PhysicsNode(String sprite, int animationCols, FrameOrientation orientation, float milisecondsPerFrame ){
        super(sprite, animationCols, orientation, milisecondsPerFrame);
    }

    public PhysicsNode(int color, float width, float height) {
        super(color, width, height);
    }

    @Override
    public void update(float deltaTime) {
        Vector2 current = this.position;
        updatePhysics(deltaTime);
        super.update(deltaTime);
        lastPosition = current;
    }

    public void updatePhysics(float deltaTime){
        if( physics == null ){
            physics = Physics.getInstance();
        }

        momentum = momentum.add(acceleration.multiply(deltaTime));
        momentum = momentum.add(physics.getGravity().multiply(deltaTime));
        position = position.add(momentum.multiply(physics.getPixelsPerMeter()));
    }

    @Override
    public void onContact(Node other) {
        this.visible = true;
    }
}
