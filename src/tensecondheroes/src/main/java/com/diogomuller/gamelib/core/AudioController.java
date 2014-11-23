package com.diogomuller.gamelib.core;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Diogo on 17/11/2014.
 */
public class AudioController {

    //region Constants
    public static final int MAX_STREAMS = 10;
    //endregion Constants

    //region Static Attributes
    /** Media Player, for Music. */
    private static MediaPlayer mediaPlayer = new MediaPlayer();
    /** Sound Pool, for Sound Effects. */
    private static SoundPool soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);;
    /** Sound Map */
    private static Map<String, Integer> loadedSounds = new TreeMap<String, Integer>();
    //endregion Static Attributes

    //region Music Methods
    public static void playMusic(String file){
        if( mediaPlayer != null ){
            try {
                if( mediaPlayer.isPlaying() ) stopMusic();
            } catch( IllegalStateException ieex ) {
                Log.d("Media Player", "Media Player has an Illegal state. Ignored.");
            }
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
        try {
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        } catch(IllegalStateException ieex) {
            Log.d("Audio Player - Resume", "Invalid State.");
        }
    }
    //endregion Music Methods

    //region Sound Methods
    public static void playSound(String path){
        if(!loadedSounds.containsKey(path)){
            try {
                AssetFileDescriptor descriptor = Assets.getAssetManager().openFd(path);
                loadedSounds.put(path, soundPool.load(descriptor, 1));
            } catch ( IOException ioex ) {
                Log.e("Sound Player", "Error opening sound file", ioex);
            }
        }

        soundPool.play(loadedSounds.get(path),
                1, // Left Volume
                1, // Right Volume
                1, // Priority
                0, // Loop
                1.0f); // Rate
    }
    //endregion Sound Methods
}
