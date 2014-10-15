package com.diogomuller.gamelib.physics;

import com.diogomuller.gamelib.node.Node;

import org.jbox2d.dynamics.BodyDef;

/**
 * Created by Diogo on 13/10/2014.
 */
public class BodyDefinition {
    private Node actor;
    private BodyDef definition;

        public BodyDefinition(Node actor, BodyDef bodyDef) {
            this.definition = bodyDef;
            this.actor = actor;
        }

    public Node getActor() { return actor; }
    public BodyDef getDefinition() { return definition; }
}
