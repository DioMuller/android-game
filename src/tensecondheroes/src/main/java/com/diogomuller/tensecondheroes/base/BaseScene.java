package com.diogomuller.tensecondheroes.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.diogomuller.gamelib.core.Assets;
import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.GameScene;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.tensecondheroes.activities.MainGameActivity;

import java.util.List;

/**
 * Created by Diogo on 09/11/2014.
 */
public class BaseScene extends GameScene {
    private static final float HEIGHT = 320.0f;
    protected MainGameActivity parentActivity;
    private Paint guiPaint = new Paint();

    public BaseScene(Context context, SceneView view){
        super(context, view);

        parentActivity = (MainGameActivity) context;
        guiPaint.setTypeface(Typeface.createFromAsset(Assets.getAssetManager(), "Fonts/ChalkDust.ttf"));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        parentActivity.decreaseTimer(deltaTime);
    }

    @Override
    public boolean draw(Canvas canvas, Matrix matrix) {
        if( super.draw(canvas, matrix) ) {
            drawGui(canvas);
            return true;
        }

        return false;
    }

    public void drawGui(Canvas canvas){
        guiPaint.setColor(Color.RED);
        guiPaint.setTextSize(16);
        canvas.drawText("Score: " + parentActivity.getScore(), 15, 15, guiPaint);
        canvas.drawText("Best: " + parentActivity.getHighscore(), 15, 30, guiPaint);
        canvas.drawText("Lives: " + parentActivity.getLives(), getSize().getX() - 80, 15, guiPaint);

        int time = (int) parentActivity.getRemainingTime();

        if( time < 3 ){
            guiPaint.setColor(Color.RED);
            guiPaint.setTextSize(72);
        } else if( time < 5 ) {
            guiPaint.setColor(Color.YELLOW);
            guiPaint.setTextSize(56);
        } else {
            guiPaint.setColor(Color.WHITE);
            guiPaint.setTextSize(42);
        }

        if(parentActivity.showCounter()) {
            canvas.drawText(Integer.toString(time), getSize().getX() - 80, HEIGHT - 20, guiPaint);
        }
    }
}
