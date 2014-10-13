package com.diogomuller.gamelib.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import java.util.Vector;

/**
 * Created by Diogo on 13/10/14.
 */
public class Physics {
    public static int velIterations = 6;
    public static int posIterations = 6;

    private static int bodyCount = 0;

    private static Vec2 gravity = new Vec2(0,0);

    private static final Vector<Body> bodyDestroyQueue = new Vector<Body>();
    private static final Vector<BodyDefinition> bodyCreateQueue = new Vector<BodyDefinition>();

    private static PhysicsThread pThread = null;

    private static class PhysicsThread extends Thread {
        public boolean stop = false;
        private boolean running = false;
        private World physicsWorld = null;

        public boolean isRunning() { return running; }

        @Override
        public void run() {
            running = true;

            physicsWorld = new World(gravity);
            physicsWorld.setAllowSleep(true);

            long lastTime, currentTime = System.nanoTime();

            while(!stop) {
                lastTime = currentTime;
                currentTime = System.nanoTime();
                float dt = (currentTime - lastTime) / 1000000.0f;

                physicsWorld.step(dt, velIterations, posIterations);
            }

            running = false;
        }
    }
}
