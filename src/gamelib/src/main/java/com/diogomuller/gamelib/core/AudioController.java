package com.diogomuller.gamelib.core;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Diogo on 17/11/2014.
 */
public class AudioController {

    //region Constants
    public static final int MAX_STREAMS = 10;
    //endregion Constants

    //region Static Attributes
    /**
     * Media Player, for Music.
     */
    private static MediaPlayer mediaPlayer = new MediaPlayer();
    /** Sound Pool, for Sound Effects.
   */
    private static SoundPool soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);;
    //endregion Static Attributes

    //region Music Methods
    public static void playMusic(String file){
        if( mediaPlayer.isPlaying() ){
            stopMusic();
        }

        try {
            AssetFileDescriptor descriptor = Assets.getAssetManager().openFd(file);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);

            mediaPlayer.start();
        } catch( IOException ioex ) {
            Log.e("Music Player", "Error opening music file", ioex);
        }
    }

    public static void stopMusic(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public static void pauseMusic(){
        mediaPlayer.pause();
    }

    public static void resumeMusic(){
        if(!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }
    //endregion Music Methods
}
