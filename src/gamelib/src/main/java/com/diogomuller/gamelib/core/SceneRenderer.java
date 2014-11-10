package com.diogomuller.gamelib.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView.Renderer;

import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.Node;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Diogo on 04/10/2014.
 */
public class SceneRenderer implements Renderer, Node {

    //region Singleton
    private static SceneRenderer instance = null;
    public static SceneRenderer getCurrentInstance() { return instance; }
    //endregion Singleton

    //region Constants
    final int VERTEX_SIZE = (2 + 4) * 4;
    //endregion Constants

    //region Attributes
    private float startTime;
    private float elapsedTime;
    protected Context context;
    private Matrix matrix = new Matrix();
    private FloatBuffer vertices;
    private final int width = 800;
    private final int height = 480;
    private int texScene = -1;
    private final Bitmap baseBitmap;
    private FPSCounter fps;
    protected boolean showFps = false;

    private List<Node> children;
    //endregion Attributes

    //region Constructor
    public SceneRenderer(Context context) {
        this.context = context;

        instance = this;

        children = new ArrayList<Node>();

        startTime = System.nanoTime();
        elapsedTime = 0;

        fps = new FPSCounter();

        baseBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }
    //endregion Constructor

    //region Renderer Methods
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        startTime = System.nanoTime();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, this.width, this.height);
    }

    @Override
    public final void onDrawFrame(GL10 gl) {
        Canvas canvas = new Canvas(baseBitmap);
        //Paint paint = new Paint();

        //paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawRGB(255,255,0);

        //region Calculate Delta Time
        float deltaTime = (System.nanoTime() - startTime) / 1000000.0f;
        startTime = System.nanoTime();
        elapsedTime += deltaTime;
        //endregion Calculate Delta Time

        //region Game Cycle
        update(deltaTime);
        draw(canvas, matrix);
        //endregion Game Cycle

        //region Rendering
        texScene = loadTexture(gl, canvas, texScene);

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, this.width, 0, this.height, -1, 1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * VERTEX_SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertices = byteBuffer.asFloatBuffer();
        vertices.put(new float[]{
                0.0f, height, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f,
                width, height, 1.0f, 0.0f,
                width, 0.0f, 1.0f, 1.0f
        });
        vertices.flip();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, texScene);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        vertices.position(0);
        gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);
        vertices.position(2);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(240, 400, 0);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
        //endregion Rendering
    }
    //endregion Renderer Methods

    //region Helper Rendering Methods
    public int loadTexture(GL10 gl, Canvas canvas, int tex) {
        // Create Texture
        if(tex != -1){
            int[] deletetexture = { tex };
            gl.glDeleteTextures(1, deletetexture, 0);
        }

        int[] textureIds = new int[1];
        gl.glGenTextures(1, textureIds, 0);
        int textureId = textureIds[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);

        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_LINEAR);

        gl.glTexParameterf(
                GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

        return textureId;
    }
    //endregion Helper Rendering Methods

    //region Node Methods

    //region Game Cycle
    @Override
    public void update(float deltaTime) {
        if( showFps ) fps.update(deltaTime);
        for(Node child : children) {
            child.update(deltaTime);
        }
    }

    @Override
    public boolean draw(Canvas canvas, Matrix matrix){
        if( showFps ) fps.draw(canvas);
        for(Node child : children) {
            child.draw(canvas, matrix);
        }

        return true;
    }
    //endregion Game Cycle

    //region Getters and Setters
    @Override
    public void setPosition(Vector2 position) {
        // Nothing else to do.
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(0,0);
    }

    @Override
    public void setSize(Vector2 size) {
        // Nothing else to do.
    }

    @Override
    public Vector2 getSize() {
        return new Vector2(0,0);
    }

    @Override
    public void setRotation(float rotation) {
        // Nothing else to do.
    }

    @Override
    public float getRotation() {
        return 0;
    }

    @Override
    public void setScale(Vector2 scale) {
        // Nothing else to do.
    }

    @Override
    public Vector2 getScale() {
        return new Vector2(1,1);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setVisible(boolean visible) {
        // Nothing else to do.
    }

    @Override
    public boolean getVisible() {
        return true;
    }

    //region Node Methods
    @Override
    public void addChild(Node node) {
        children.add(node);
        node.setParent(this);
    }

    @Override
    public void removeChild(Node node) {
        children.remove(node);
    }

    @Override
    public void setParent(Node parent) {
        // Nothing else to do.
    }
    //endregion Node Methods

    //endregion Node Methods
}
