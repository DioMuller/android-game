package com.diogomuller.gamelib.node;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import com.diogomuller.gamelib.core.Assets;

import org.jbox2d.common.Vec2;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Diogo on 21/10/2014.
 */
public class BitmapNode extends BasicNode {
    //region Attributes
    protected String imagePath;
    protected int animationCols;
    protected Bitmap[] animationFrames;
    //endregion Attributes

    public BitmapNode(String sprite, int animationCols, FrameOrientation orientation) {
        super();

        this.imagePath = sprite;
        this.animationCols = animationCols;
        this.animationFrames = new Bitmap[animationCols];

        AssetManager manager = Assets.getAssetManager();

        if(manager != null) {
            try{
                // Load Bitmap
                InputStream stream = manager.open(sprite);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                stream.close();

                // Divide Bitmap
                if( orientation == FrameOrientation.HORIZONTAL){
                    this.size = new Vec2(bitmap.getWidth() / animationCols, bitmap.getHeight());
                }
                else {
                    this.size = new Vec2(bitmap.getWidth(), bitmap.getHeight() / animationCols);
                }

                for( int i = 0; i < animationCols; i++ ){
                    if( orientation == FrameOrientation.VERTICAL)
                        animationFrames[i] = Bitmap.createBitmap(bitmap, 0, (int) (i * size.y), (int) size.x, (int) size.y);
                    else
                        animationFrames[i] = Bitmap.createBitmap(bitmap, (int) (i * size.x), 0, (int) size.x, (int) size.y);
                }

            } catch( IOException ioex ) {
                Log.e("BitmapNode Id = " + getId(), ioex.getMessage() );
            }


        }
    }

    //region Game Cycle Methods
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean draw(Canvas canvas, Matrix transformations) {
        if( !super.draw(canvas, transformations) ) return false;

        float halfWidth = size.x / 2.0f;
        float halfHeight = size.y / 2.0f;

        Matrix matrix = new Matrix(transformations);
        matrix.preTranslate(position.x - halfWidth, position.y - halfHeight);
        matrix.preScale(scale.x, scale.y, halfWidth, halfHeight );
        matrix.preRotate(rotation, halfWidth, halfHeight);

        return true;
    }
    //endregion Game Cycle Methods

    public enum FrameOrientation {
        HORIZONTAL,
        VERTICAL
    }
}
