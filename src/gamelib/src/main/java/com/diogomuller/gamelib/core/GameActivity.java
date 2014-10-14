package com.diogomuller.gamelib.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Diogo on 04/10/2014.
 */
public class GameActivity extends Activity {
    private Stack<View> pastViews;
    private View currentView = null;

    private static int nextId = 0;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        pastViews = new Stack<View>();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    @Override
    public void onBackPressed(){
        if(pastViews.isEmpty()) {
            super.onBackPressed();
            return;
        }

        currentView = pastViews.pop();
    }

    public static int getNextId() {
        return (nextId++);
    }
}
