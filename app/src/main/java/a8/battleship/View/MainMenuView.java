package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import a8.battleship.Logic.Variables;
import a8.battleship.R;

/**
 *This class shows the Main menu where you can choose between 'New game', 'Settings' and 'Credits'.
 */
public class MainMenuView extends ActionBarActivity implements View.OnClickListener{

    private Button startButton, settingsButton, creditsButton;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets.
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
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
     * A method that is called when a button is pushed.
     * @param v a reference to the clicked button.
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