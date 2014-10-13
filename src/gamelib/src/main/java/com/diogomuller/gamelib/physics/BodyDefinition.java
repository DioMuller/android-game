package com.diogomuller.gamelib.physics;

import org.jbox2d.dynamics.BodyDef;

/**
 * Created by Diogo on 13/10/2014.
 */
public class BodyDefinition {
    private int actorID;
    private BodyDef bd;

        public BodyDefinition(int _actorID, BodyDef _bd) {
            bd = _bd;
            actorID = _actorID;
        }

    public int getActorID() { return actorID; }
    public BodyDef getBd() { return bd; }
}
