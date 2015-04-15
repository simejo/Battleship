package a8.battleship.View;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import a8.battleship.R;

//This class shows credits to the game
public class CreditsView extends ActionBarActivity {

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_view);
    }
}
