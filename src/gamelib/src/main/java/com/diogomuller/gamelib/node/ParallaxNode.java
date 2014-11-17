package com.diogomuller.gamelib.node;

import com.diogomuller.gamelib.math.Vector2;

/**
 * Created by Diogo on 17/11/2014.
 */
public class ParallaxNode extends BasicNode {
    private static final int IMAGES = 3;

    private BitmapNode[] nodes;
    private Vector2 halfSize;
    private Vector2 imageSize;
    private float speed;

    public ParallaxNode(String image, Vector2 size, float speed){
        nodes = new BitmapNode[IMAGES];
        halfSize = size.divide(2.0f);
        imageSize = size;

        for(int i = 0; i < IMAGES; i++){
            nodes[i] = new BitmapNode(image, 1, BitmapNode.FrameOrientation.HORIZONTAL, 0.0f);
            Vector2 bmpsize = nodes[i].getSize();
            nodes[i].setScale(new Vector2(size.getX()/bmpsize.getX(), size.getY()/bmpsize.getY()));
            nodes[i].setPosition( new Vector2(
                    halfSize.getX() + i * size.getX(),
                    halfSize.getY()
            ) );

            addChild(nodes[i]);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for(Node node : children) {
            Vector2 newPosition = node.getPosition().add(new Vector2(deltaTime * speed, 0.0f));

            if( newPosition.getX() < -halfSize.getX()) {
                newPosition = new Vector2(
                        halfSize.getX() + (IMAGES - 1) * size.getX(),
                        halfSize.getY()
                );
            }

            node.setPosition(newPosition);
        }
    }
}
