package com.diogomuller.gamelib.physics;

import org.jbox2d.dynamics.BodyDef;

/**
 * Created by Diogo on 13/10/2014.
 */
public class BodyDefinition {
    private int actorID;
    private BodyDef definition;

        public BodyDefinition(int actorID, BodyDef bodyDef) {
            this.definition = bodyDef;
            this.actorID = actorID;
        }

    public int getActorID() { return actorID; }
    public BodyDef getBd() { return definition; }
}
