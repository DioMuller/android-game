package com.diogomuller.tensecondheroes.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.diogomuller.gamelib.core.AudioController;
import com.diogomuller.gamelib.core.SceneView;
import com.diogomuller.gamelib.math.Vector2;
import com.diogomuller.gamelib.entities.Entity;
import com.diogomuller.gamelib.physics.Physics;
import com.diogomuller.tensecondheroes.activities.MainGameActivity;

import java.util.List;

/**
 * Created by Diogo on 09/11/2014.
 */
public class BaseScene extends SceneView {
    private Physics physics = Physics.getInstance();
    private static final float HEIGHT = 320.0f;
    protected MainGameActivity parentActivity;
    private Paint guiPaint = new Paint();

    public BaseScene(Context context){
        super(context, HEIGHT);

        this.showFps = false;

        parentActivity = (MainGameActivity) context;

        physics.setGravity(new Vector2(0, 5));
        physics.setPixelsPerMeter(4);
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
        guiPaint.setColor(Color.WHITE);
        guiPaint.setTextSize(16);
        canvas.drawText("Score: " + parentActivity.getScore(), 15, 15, guiPaint);
        canvas.drawText("Lives: " + parentActivity.getLives(), getSize().getX() - 80, 15, guiPaint);
        guiPaint.setTextSize(42);
        canvas.drawText(new Integer((int) parentActivity.getRemainingTime()).toString(), getSize().getX() - 80, HEIGHT - 20, guiPaint);
    }
}
