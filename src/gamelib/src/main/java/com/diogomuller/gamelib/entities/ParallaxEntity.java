package com.diogomuller.gamelib.entities;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.diogomuller.gamelib.math.Vector2;

/**
 * Created by Diogo on 17/11/2014.
 */
public class ParallaxEntity extends BasicEntity {
    private static final int IMAGES = 3;

    private BitmapEntity[] nodes;
    private Vector2 halfSize;
    private Vector2 imageSize;
    private float speed;

    public ParallaxEntity(String image, Vector2 size, float speed){
        nodes = new BitmapEntity[IMAGES];
        halfSize = size.divide(2.0f);
        imageSize = size;

        for(int i = 0; i < IMAGES; i++){
            nodes[i] = new BitmapEntity(image, 1, BitmapEntity.FrameOrientation.HORIZONTAL, 0.0f);
            Vector2 bmpsize = nodes[i].getSize();
            nodes[i].setScale(new Vector2(size.getX()/bmpsize.getX(), size.getY()/bmpsize.getY()));
            nodes[i].setPosition( new Vector2(
                    halfSize.getX() + i * size.getX(),
                    halfSize.getY()
            ) );
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for(Entity entity : nodes) {
            Vector2 newPosition = entity.getPosition().add(new Vector2(deltaTime * speed, 0.0f));

            if( newPosition.getX() < -halfSize.getX()) {
                newPosition = new Vector2(
                        halfSize.getX() + (IMAGES - 1) * size.getX(),
                        halfSize.getY()
                );
            }

            entity.setPosition(newPosition);
        }
    }

    @Override
    public boolean draw(Canvas canvas, Matrix transformations) {
        if( super.draw(canvas, transformations) ) {
            for(Entity entity : nodes) {
                entity.draw(canvas, transformations);
            }
        }

        return true;
    }
}
