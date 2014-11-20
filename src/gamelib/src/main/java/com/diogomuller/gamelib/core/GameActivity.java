package com.diogomuller.gamelib.core;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.diogomuller.gamelib.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Diogo on 04/10/2014.
 */
public class GameActivity extends Activity implements OnTouchListener {

    //region Static Attributes
    private static int nextId = 0;
    private static final int MAX_TOUCHES = 10;
    //endregion Static Attributes

    //region Attributes
    private boolean created = false;
    protected SceneView screen = null;

    private List<Vector2> touchPoints = new ArrayList<Vector2>();
    //endregion Attributes

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Assets.setAssetManager(this.getAssets());
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        if( screen == null ) {
            screen = new SceneView(this, 320.0f);
            screen.setOnTouchListener(this);
        }

        setContentView(screen);
        created = true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        screen.resume();
        AudioController.resumeMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        screen.pause();
        AudioController.pauseMusic();
    }

    public void loadScene(GameScene scene){
        screen.loadScene(scene);
    }

    //region Static Methods
    public static int getNextId() {
        return (nextId++);
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        int pointerIndex = MotionEventCompat.getActionIndex(event);
        int pointerCount = event.getPointerCount();

        touchPoints.clear();

        for (int i=0; i< pointerCount; i++) {
            int pointerId = event.getPointerId(i);
            touchPoints.add(new Vector2(event.getX(), event.getY()));
        }

        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                screen.getCurrentScene().onTouchEntered(touchPoints);
                break;
            case MotionEvent.ACTION_MOVE:
                screen.getCurrentScene().onTouchMoved(touchPoints);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                screen.getCurrentScene().onTouchExit(touchPoints);
                break;

            default:
                break;
        }

        return true;
    }
    //endregion Static Methods
}
