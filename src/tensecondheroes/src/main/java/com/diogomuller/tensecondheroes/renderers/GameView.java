package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Color;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.node.BitmapNode;
import com.diogomuller.gamelib.node.Node;
import com.diogomuller.gamelib.node.ParallaxNode;
import com.diogomuller.gamelib.node.PhysicsNode;
import com.diogomuller.gamelib.physics.Physics;
import com.diogomuller.tensecondheroes.scenes.FlappyScene;

import java.util.List;

/**
 * Created by Diogo on 09/11/2014.
 */
public class GameView extends SceneView {
    private Physics physics = Physics.getInstance();
    private static final float HEIGHT = 640.0f;
    private Node currentScene = null;

    public GameView(Context context){
        super(context, HEIGHT);

        this.showFps = true;

        physics.setGravity(new Vector2(0, 5));
        physics.setPixelsPerMeter(4);

        loadScene(new FlappyScene(this.getSize()));
    }

    @Override
    public void onTouchEntered(List<Vector2> points) {
        AudioController.playSound("Sound/drop.wav");
    }

    public void loadScene(Node scene){
        children.clear();
        addChild(scene);
    }
}
