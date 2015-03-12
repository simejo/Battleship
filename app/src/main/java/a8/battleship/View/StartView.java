package a8.battleship.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class StartView extends ActionBarActivity implements View.OnClickListener{

    private RadioButton rbOnePlayer, rbTwoPlayer;
    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        rbOnePlayer = (RadioButton) findViewById(R.id.radioButtonOnePlayer);
        rbTwoPlayer = (RadioButton) findViewById(R.id.radioButtonTwoPlayer);
        buttonStartGame = (Button) findViewById(R.id.buttonStartGame);

    }
    @Override
    public void onClick(View v){
        //if(v.getId() == R.id.buttonStartGame){
        //
        //}

    }

}
