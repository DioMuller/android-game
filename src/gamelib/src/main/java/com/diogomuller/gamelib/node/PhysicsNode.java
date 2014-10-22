package com.diogomuller.gamelib.node;

import org.jbox2d.dynamics.Body;

/**
 * Created by Diogo on 22/10/2014.
 */
public class PhysicsNode extends BitmapNode {

    //region Attributes
    Body body;
    //endregion Attributes

    public PhysicsNode(String sprite, int animationCols){
        super(sprite, animationCols);
    }
}
