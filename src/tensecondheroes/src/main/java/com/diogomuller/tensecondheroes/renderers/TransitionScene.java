package com.diogomuller.tensecondheroes.renderers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.diogomuller.gamelib.core.Assets;
import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.tensecondheroes.base.BaseScene;
import com.diogomuller.tensecondheroes.game.MinigameInfo;
import com.diogomuller.tensecondheroes.game.Minigames;

/**
 * Created by Diogo on 16/11/2014.
 */
public class TransitionScene extends BaseScene {
    private int level;
    private MinigameInfo minigameInfo;
    private Paint paint = new Paint();
    private Vector2 position;

    public TransitionScene(Context context, SceneView view,  int level){
        super(context, view);
        this.level = level;
        minigameInfo = Minigames.getInfo(level);
        AudioController.playMusic("Music/Score Time.ogg");

        paint.setTypeface(Typeface.createFromAsset(Assets.getAssetManager(), "Fonts/ChalkDust.ttf"));

        position = getSize().divide(2.0f);

        paint.setColor(Color.WHITE);
        paint.setTextSize(32);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean draw(Canvas canvas, Matrix matrix) {
        if( super.draw(canvas, matrix) ) {
            canvas.drawText(minigameInfo.getName(), position.getX(), position.getY(), paint);
            return true;
        }

        return false;
    }
}
