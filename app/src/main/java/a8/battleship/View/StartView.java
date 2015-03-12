package a8.battleship.View;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.RadioButton;

import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class StartView extends ActionBarActivity{

    //Choose between one-player or two players

    private RadioButton rbOnePlayer, rbTwoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        rbOnePlayer = (RadioButton) findViewById(R.id.radioButtonOnePlayer);
        rbTwoPlayer = (RadioButton) findViewById(R.id.radioButtonTwoPlayer);

    }

}
