package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;

import com.diogomuller.tensecondheroes.base.BaseScene;

/**
 * Created by Diogo on 16/11/2014.
 */
public class TransitionScene extends BaseScene {
    private int level;

    public TransitionScene(Context context, int level){
        super(context);
        this.level = level;
    }
}
