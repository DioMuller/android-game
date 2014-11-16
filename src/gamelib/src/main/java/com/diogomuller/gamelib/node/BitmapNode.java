package com.diogomuller.gamelib.node;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.transition.Scene;
import android.util.Log;

import com.diogomuller.gamelib.core.Assets;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Diogo on 21/10/2014.
 */
public class BitmapNode extends BasicNode {

    //region Enum
    public enum FrameOrientation {
        HORIZONTAL,
        VERTICAL
    }
    //endregion Enum

    //region Attributes
    protected String imagePath;
    protected int animationCols;
    protected Bitmap[] animationFrames;
    protected float canvasScale = 0.0f;
    protected float msPerFrame = 0.0f;
    protected float timeSinceLastFrame = 0.0f;
    protected int currentFrame = 0;
    //endregion Attributes

    //region Constructors
    public BitmapNode(String sprite, int animationCols, FrameOrientation orientation, float msPerFrame) {
        super();

        this.imagePath = sprite;
        this.animationCols = animationCols;
        this.animationFrames = new Bitmap[animationCols];
        this.msPerFrame = msPerFrame;

        AssetManager manager = Assets.getAssetManager();

        if(manager != null) {
            try{
                // Load Bitmap
                InputStream stream = manager.open(sprite);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                stream.close();

                // Divide Bitmap
                if( orientation == FrameOrientation.HORIZONTAL){
                    this.size = new Vector2(bitmap.getWidth() / animationCols, bitmap.getHeight());
                }
                else {
                    this.size = new Vector2(bitmap.getWidth(), bitmap.getHeight() / animationCols);
                }

                for( int i = 0; i < animationCols; i++ ){
                    if( orientation == FrameOrientation.VERTICAL)
                        animationFrames[i] = Bitmap.createBitmap(bitmap, 0, (int) (i * size.getY()), (int) size.getX(), (int) size.getY());
                    else
                        animationFrames[i] = Bitmap.createBitmap(bitmap, (int) (i * size.getX()), 0, (int) size.getX(), (int) size.getY());
                }

            } catch( IOException ioex ) {
                Log.e("BitmapNode Id = " + getId(), ioex.getMessage() );
            }
        }
    }

    public BitmapNode(int color, float width, float height) {
        super();

        this.imagePath = null;
        this.animationCols = 1;
        this.animationFrames = new Bitmap[1];
        this.msPerFrame = 0.0f;

        this.animationFrames[0] = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.animationFrames[0]);

        canvas.drawColor(color);

        this.size = new Vector2(width, height);
    }
    //endregion Constructors

    //region Game Cycle Methods
    @Override
    public void update(float deltaTime) {
        if( canvasScale == 0.0f ){
            canvasScale = SceneView.getCurrentScene().getCanvasScale();
        }

        timeSinceLastFrame += deltaTime;

        if( msPerFrame < timeSinceLastFrame ){
            currentFrame = (currentFrame + 1) % animationCols;
            timeSinceLastFrame -= msPerFrame;
        }

        super.update(deltaTime);
    }

    @Override
    public boolean draw(Canvas canvas, Matrix transformations) {
        if( !super.draw(canvas, transformations) ) return false;

        float halfWidth = (size.getX() / 2.0f) * canvasScale;
        float halfHeight = (size.getY() / 2.0f) * canvasScale;

        Matrix matrix = new Matrix(transformations);
        matrix.preTranslate( ( (parent.getPosition().getX() + position.getX()) * canvasScale) - halfWidth, ((parent.getPosition().getY() + position.getY()) * canvasScale) - halfHeight);
        matrix.preScale((parent.getScale().getX() + scale.getX()) * canvasScale, (parent.getScale().getY() + scale.getY()) * canvasScale, halfWidth, halfHeight );
        matrix.preRotate(parent.getRotation() + rotation, halfWidth, halfHeight);

        canvas.drawBitmap(animationFrames[currentFrame], matrix, null);

        return true;
    }
    //endregion Game Cycle Methods


    @Override
    public void onContact(Node other) {
        this.visible = false;
    }
}
