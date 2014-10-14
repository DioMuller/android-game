package com.diogomuller.testgame;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.diogomuller.gamelib.core.GameActivity;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.node.RectangleNode;


public class MainActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class TestScreen extends SceneView {
        private RectangleNode bob;

        public TestScreen(Context context) {
            super(context);

            bob = new RectangleNode(null, 100, 100);
        }

        @Override
        protected void draw(Canvas canvas, float deltaTime) {
            bob.draw(canvas, deltaTime);
        }

        @Override
        protected void update(float deltaTime) {
            bob.update(deltaTime);
        }
    }
}
