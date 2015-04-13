package a8.battleship.View;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import a8.battleship.Logic.Constants;
import a8.battleship.R;

public class MainMenuView extends ActionBarActivity implements View.OnClickListener{

    private Button startButton, settingsButton, creditsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_view);

        startButton = (Button) findViewById(R.id.buttonStart);
        settingsButton = (Button) findViewById(R.id.buttonSettings);
        creditsButton = (Button) findViewById(R.id.buttonCredits);

        startButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        creditsButton.setOnClickListener(this);
        Constants.screenHeight = this.getResources().getDisplayMetrics().heightPixels;
        Constants.screenWidth = this.getResources().getDisplayMetrics().widthPixels;

        //sounds
        //Constants.launch = MediaPlayer.create(this, R.raw.launch);
        Constants.hit = MediaPlayer.create(this, R.raw.hit);
        Constants.miss = MediaPlayer.create(this, R.raw.miss);
        Constants.backgroundMusic = MediaPlayer.create(this, R.raw.music);

        Constants.amSound = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Constants.amSound.setStreamVolume(AudioManager.STREAM_MUSIC, Constants.amSound.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        Constants.amMusic = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Constants.amMusic.setStreamVolume(AudioManager.STREAM_MUSIC, Constants.amMusic.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        Constants.backgroundMusic.start();
        Constants.backgroundMusic.setLooping(true);


    }

    public void onClick(View v){
        if(v.getId() == R.id.buttonCredits){
            startActivity(new Intent(MainMenuView.this, CreditsView.class));
        }
        else if(v.getId() == R.id.buttonStart){
            startActivity(new Intent(MainMenuView.this, StartView.class));
        }
        else if(v.getId() == R.id.buttonSettings){
            startActivity(new Intent(MainMenuView.this, SettingsView.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}