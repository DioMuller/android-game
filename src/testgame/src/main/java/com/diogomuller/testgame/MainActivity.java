package com.diogomuller.testgame;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.gamelib.core.SceneView;

import org.jbox2d.common.Vec2;


public class MainActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestScreen(this));
    }

    public class TestScreen extends SceneView {

        public TestScreen(Context context) {
            super(context);
        }

        @Override
        protected void draw(Canvas canvas, float deltaTime) {
        }

        @Override
        protected void update(float deltaTime) {
        }
    }
}
