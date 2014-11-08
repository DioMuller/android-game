package com.diogomuller.gamelib.core;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Stack;

/**
 * Created by Diogo on 04/10/2014.
 */
public class GameActivity extends Activity {

    //region Static Attributes
    private static int nextId = 0;
    //endregion Static Attributes

    //region Attributes
    private GLSurfaceView screen = null;
    //endregion Attributes

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        screen = new GLSurfaceView(this);
        setContentView(screen);
    }

    public void loadScene(Renderer renderer){
        screen.setRenderer(renderer);
    }

    //region Static Methods
    public static int getNextId() {
        return (nextId++);
    }
    //endregion Static Methods
}
