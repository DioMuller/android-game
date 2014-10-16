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

import org.jbox2d.common.Vec2;


public class MainActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestScreen(this));
    }

    public class TestScreen extends SceneView {
        private RectangleNode bob;
        private RectangleNode bobo;

        public TestScreen(Context context) {
            super(context);

            bob = new RectangleNode(10, 10, 15, 24, 30);
            bobo = new RectangleNode(10,10, 10, 20, 0);

            this.setGravity(new Vec2(0,-10));
        }

        @Override
        protected void draw(Canvas canvas, float deltaTime) {
            bob.draw(canvas, deltaTime);
            bobo.draw(canvas, deltaTime);
        }

        @Override
        protected void update(float deltaTime) {
            bob.update(deltaTime);
            bobo.update(deltaTime);
        }
    }
}
