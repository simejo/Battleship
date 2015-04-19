package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import a8.battleship.R;

/**
 * This class shows credits to the game
 */
public class CreditsView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonBack;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_view);
        buttonBack = (Button) findViewById(R.id.buttonCreditsBack);
        buttonBack.setOnClickListener(this);
    }

    /**
     * The onClick method is called when one of its observers (buttons) are pushed.
     * @param v The source view which is pushed.
     */
    public void onClick(View v){
        if(v.getId() == R.id.buttonCreditsBack){
            Intent i = new Intent(CreditsView.this, MainMenuView.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}

