package de.openmuseau.audioguide.tasks;

import android.media.MediaPlayer;
import android.os.Handler;

/**
 * Created by david on 04.09.16.
 */
public class ProgressBarUpdater implements Runnable
{
    private MediaPlayer mediaplayer;
    private Handler mediaPlayerHandler;

    //Why this handler?
    private int mediaplayerProgress;

    public ProgressBarUpdater(MediaPlayer player, Handler handler) {

        this.mediaplayer = player;
        this.mediaPlayerHandler = handler;
    }



    public void run() {

        while (mediaplayer.isPlaying())
        {
            mediaplayerProgress = calcCurrProgress();
            //Log.i("main", "" + mediaplayerProgress);

            mediaPlayerHandler.sendEmptyMessage(0);
        }
    }


    private int calcCurrProgress()
    {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }

        int total = mediaplayer.getDuration();

        //100/total = x / current -> x = 100 * current/total
        int curr = mediaplayer.getCurrentPosition();

        return (100*curr)/total;

    }

    public int getMediaplayerProgress() {
        return mediaplayerProgress;
    }

}
