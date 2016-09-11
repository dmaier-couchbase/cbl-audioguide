package de.openmuseau.audioguide;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import de.openmuseau.audioguide.model.EntriesModel;
import de.openmuseau.audioguide.model.Entry;
import de.openmuseau.audioguide.tasks.ProgressBarUpdater;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //-- Model

    /**
     * The model of this view
     */
    private EntriesModel model;


    //-- Media player

    /**
     * The media player to play the MP3 files
     */
    private MediaPlayer mediaPlayer;

    /**
     * Required in order to update the progress of the media player in the UI
     */
    private ProgressBarUpdater mediaplayerProgressUpdater;


    /**
     * Using this handler because only the UI thread is allowed to change the UI
     */
    private Handler mediaplayerProgressHandler = new Handler()
    {
        public void handleMessage(Message msg) {

            updateProgress();
        };
    };


    //-- Init
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        onCreateDefault(savedInstanceState);

        //Disable the seek bar
        SeekBar seek = (SeekBar) findViewById(R.id.playprogress);
        seek.setEnabled(false);

        //Set the model
        setModel();


    }


    //-- Customs

    /**
     * Sets the model of this activity
     *
     * TODO: EntriesModel should be read from Couchbase Lite
     */
    public void setModel() {

        model = new EntriesModel();

        Entry e = new Entry();
        e.setId(1);
        e.setTitle("About this application");
        e.setDesc("This application was developed for Museums in order to allow them to turn common" +
                "Smartphones into Audio Guide devices.");
        e.setAudio("im");
        List<String> images = new ArrayList<>();
        images.add("ip0");
        images.add("ip1");

        e.setImages(images);

        List<Entry> entries = new ArrayList<>();
        entries.add(e);

        model.setEntries(entries);
    }

    /**
     * Update the progress of the media player
     */
    public void updateProgress()
    {
        SeekBar mediaPlayerProgressBar = (SeekBar) findViewById(R.id.playprogress);

        int progress = mediaplayerProgressUpdater.getMediaplayerProgress();

        mediaPlayerProgressBar.setProgress(progress);
    }





    //-- Defaults


    /**
     * The default initialization of this activity
     *
     * @param savedInstanceState
     */
    protected void onCreateDefault(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * What happens when back is pressed.
     *
     * Let's keep this because it's anyway planned to implement a kiosk mode where we would
     * like to disable the back button.
     *
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Create the options menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * What happens if an options menu item is selected
     *
     * We just keep this for later, but the options button is for now greyed out.
     *
     * Idea: Provide a PIN in order to change app settings, e.g. to which sync endpoint it should
     * connect
     *
      * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Implements what happens if a navigation item is selected
     *
     * For now all navigation items were removed but the menu is used in order to show
     * details about the App. I will keep this for later in case that we need future
     * navigation entries.
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Not yet implemented

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
