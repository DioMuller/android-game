package com.diogomuller.gamelib.node;

import android.graphics.Canvas;

/**
 * Created by Diogo on 21/10/2014.
 */
public class BitmapNode extends BasicNode {
    String imagePath;
    int animationCols;

    public BitmapNode(String sprite, int animationCols) {
        super();
    }

    //region Game Cycle Methods
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean draw(float deltaTime, Canvas canvas) {
        if( !super.draw(deltaTime, canvas) ) return false;

        //TODO: Draw Sprite
        return true;
    }
    //endregion Game Cycle Methods
}
