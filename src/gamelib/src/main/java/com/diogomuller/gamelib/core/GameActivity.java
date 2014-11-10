package com.diogomuller.gamelib.core;

import android.app.Activity;
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
    private boolean created = false;
    private SceneView screen = null;
    //endregion Attributes

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        if( screen == null ) {
            screen = new SceneView(this);
        }

        setContentView(screen);
        created = true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        screen.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        screen.pause();
    }

    public void loadScene(SceneView scene){
        screen = scene;
        if(created) setContentView(screen);
    }

    //region Static Methods
    public static int getNextId() {
        return (nextId++);
    }
    //endregion Static Methods
}
