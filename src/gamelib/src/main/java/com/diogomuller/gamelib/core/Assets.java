package com.diogomuller.gamelib.core;

import android.content.res.AssetManager;

/**
 * Created by Diogo on 23/10/2014.
 */
public class Assets {
    protected static AssetManager manager = null;

    public static AssetManager getAssetManager() { return manager; }
    public static void setAssetManager(AssetManager assets) { manager = assets; }
}
