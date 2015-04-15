package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import a8.battleship.Logic.Variables;
import a8.battleship.R;

/**
 *This class shows the Main menu where you can choose between 'New game', 'Settings' and 'Credits'
 */
public class MainMenuView extends ActionBarActivity implements View.OnClickListener{

    private Button startButton, settingsButton, creditsButton;

    /**
     * In this method is the MainMenuView created and the different buttons is initialized
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_view);

        startButton = (Button) findViewById(R.id.buttonStart);
        settingsButton = (Button) findViewById(R.id.buttonSettings);
        creditsButton = (Button) findViewById(R.id.buttonCredits);

        startButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        creditsButton.setOnClickListener(this);
    }

    /**
     * A method that is called when a button is pushed
     * @param v
     */
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
}