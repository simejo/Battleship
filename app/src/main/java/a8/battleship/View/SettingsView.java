package a8.battleship.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TabHost;

import a8.battleship.R;

/**
 * Created by siljechristensen on 11.03.15.
 */
public class SettingsView extends ActionBarActivity implements View.OnClickListener{

    private Button startButton, mainMenuButton;
    private RadioButton boatsRButton, girlsRButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);

        startButton = (Button) findViewById(R.id.buttonStart);
        mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
        boatsRButton = (RadioButton) findViewById(R.id.boatsRadioButton);
        girlsRButton = (RadioButton) findViewById(R.id.girlsRadioButton);

        startButton.setOnClickListener(this);
        mainMenuButton.setOnClickListener(this);
        boatsRButton.setOnClickListener(this);
        girlsRButton.setOnClickListener(this);

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

        }
        else if (girlsRButton.isChecked()) {

        }

    }
}
