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

        public TestScreen(Context context) {
            super(context);

            bob = new RectangleNode(100, 100);
            bob.setPosition(new Vec2(150, 240));
            bob.setRotation(30.0f);

            this.setGravity(new Vec2(0,-10));
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
