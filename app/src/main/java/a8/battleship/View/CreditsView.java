package a8.battleship.View;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import a8.battleship.R;

//This class shows credits to the game
public class CreditsView extends ActionBarActivity {

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_view);
    }
}
