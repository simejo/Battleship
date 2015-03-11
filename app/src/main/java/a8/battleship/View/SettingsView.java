package a8.battleship.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import a8.battleship.R;

/**
 * Created by siljechristensen on 11.03.15.
 */
public class SettingsView extends ActionBarActivity implements View.OnClickListener{

    private Button startButton, mainMenuButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_view);

        startButton = (Button) findViewById(R.id.buttonStart);
        startButton.setOnClickListener(this);

        mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
        mainMenuButton.setOnClickListener(this);


    }




    public void onClick(View v){
        if(v.getId() == R.id.buttonMainMenu){
            startActivity(new Intent(SettingsView.this, MainMenuView.class));
        }
        else if(v.getId() == R.id.buttonStart){
            startActivity(new Intent(SettingsView.this, StartView.class));
        }

    }
}
