package a8.battleship.View;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import a8.battleship.Logic.Variables;
import a8.battleship.R;

/**
 * This is the class where you can select which layout you want on the game.
 * You can choose between boats, girls or boys.
 * You can also choose if you want the background music and/or the sounds.
 * You can navigate back to main menu and to the start game view.
 */
public class SettingsView extends ActionBarActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private Button startButton, mainMenuButton;
    private RadioButton boatsRButton, girlsRButton, boysRButton;
    private CheckBox checkBoxMusic, checkBoxSound;

    /**
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);

        startButton = (Button) findViewById(R.id.buttonStart);
        mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
        boatsRButton = (RadioButton) findViewById(R.id.boatsRadioButton);
        girlsRButton = (RadioButton) findViewById(R.id.girlsRadioButton);
        boysRButton = (RadioButton) findViewById(R.id.boysRadioButton);
        checkBoxMusic = (CheckBox) findViewById(R.id.checkBoxMusicSettings);
        checkBoxSound = (CheckBox) findViewById(R.id.checkBoxSoundSettings);

        startButton.setOnClickListener(this);
        mainMenuButton.setOnClickListener(this);
        boatsRButton.setOnClickListener(this);
        girlsRButton.setOnClickListener(this);
        boysRButton.setOnClickListener(this);

        checkBoxSound.setOnCheckedChangeListener(this);
        checkBoxMusic.setOnCheckedChangeListener(this);

        checkBoxMusic.setChecked(Variables.cbBooleanMusic);
        checkBoxSound.setChecked(Variables.cbBooleanSound);

        if(checkBoxMusic.isChecked()){
            Variables.cbBooleanMusic = true;
            Variables.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
        else{
            Variables.cbBooleanMusic = false;
            Variables.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }

    }

    /**
     *
     * @param v
     */
    public void onClick(View v){
        if(v.getId() == R.id.buttonMainMenu){
            startActivity(new Intent(SettingsView.this, MainMenuView.class));
        }
        else if(v.getId() == R.id.buttonStart){
            startActivity(new Intent(SettingsView.this, StartView.class));
        }
        if(boatsRButton.isChecked()) {
            Variables.gameLayout = "boats";
        }
        else if (girlsRButton.isChecked()) {
            Variables.gameLayout = "girls";
        }
        else if (boysRButton.isChecked()) {
            Variables.gameLayout = "boys";
        }

    }

    /**
     * Checks if the checkboxes for the background music and the sounds is checked or not.
     * @param buttonView
     * @param isChecked
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if (buttonView.getId() == R.id.checkBoxSoundSettings) {
            Variables.cbBooleanSound = isChecked;
            Variables.amSound.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
        }
        else if(buttonView.getId() == R.id.checkBoxMusicSettings){
            Variables.cbBooleanMusic = isChecked;
            Variables.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
        }
    }
}
