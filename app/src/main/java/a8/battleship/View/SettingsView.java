package a8.battleship.View;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import a8.battleship.Logic.Constants;
import a8.battleship.R;

public class SettingsView extends ActionBarActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private Button startButton, mainMenuButton;
    private RadioButton boatsRButton, girlsRButton;
    private CheckBox checkBoxMusic, checkBoxSound;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);

        startButton = (Button) findViewById(R.id.buttonStart);
        mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
        boatsRButton = (RadioButton) findViewById(R.id.boatsRadioButton);
        girlsRButton = (RadioButton) findViewById(R.id.girlsRadioButton);
        checkBoxMusic = (CheckBox) findViewById(R.id.checkBoxMusicSettings);
        checkBoxSound = (CheckBox) findViewById(R.id.checkBoxSoundSettings);

        startButton.setOnClickListener(this);
        mainMenuButton.setOnClickListener(this);
        boatsRButton.setOnClickListener(this);
        girlsRButton.setOnClickListener(this);

        checkBoxSound.setOnCheckedChangeListener(this);
        checkBoxMusic.setOnCheckedChangeListener(this);

        checkBoxMusic.setChecked(Constants.cbBooleanMusic);
        checkBoxSound.setChecked(Constants.cbBooleanSound);

    }
    @Override
    public void onClick(View v){

        if(v.getId() == R.id.buttonMainMenu){
            startActivity(new Intent(SettingsView.this, MainMenuView.class));
        }
        else if(v.getId() == R.id.buttonStart){
            startActivity(new Intent(SettingsView.this, StartView.class));
        }
        if(boatsRButton.isChecked()) {
            Constants.gameLayout = "boats";
        }
        else if (girlsRButton.isChecked()) {
            Constants.gameLayout = "girls";

        }

    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if (buttonView.getId() == R.id.checkBoxSoundSettings) {

            Constants.cbBooleanSound = isChecked;
            Constants.amSound.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);
        }
        else if(buttonView.getId() == R.id.checkBoxMusicSettings){

            Constants.cbBooleanMusic = isChecked;
            Constants.amMusic.setStreamMute(AudioManager.STREAM_MUSIC, !isChecked);

        }

    }
}
