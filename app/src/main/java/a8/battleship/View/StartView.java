package a8.battleship.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class StartView extends ActionBarActivity implements View.OnClickListener{

    //Choose between one-player or two players

    private RadioButton rbOnePlayer, rbTwoPlayer;
    private Button buttonStartGame;
    private TextView tvPlayerOne, tvPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        rbOnePlayer = (RadioButton) findViewById(R.id.radioButtonOnePlayer);
        rbTwoPlayer = (RadioButton) findViewById(R.id.radioButtonTwoPlayer);
        buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        tvPlayerOne = (EditText) findViewById(R.id.tvPlayerOne);
        tvPlayerTwo = (EditText) findViewById(R.id.tvPlayerTwo);

        tvPlayerOne.setAlpha(0);
        tvPlayerTwo.setAlpha(0);

        if(rbOnePlayer.isChecked()){
            tvPlayerTwo.setAlpha(1);
        }

    }
    @Override
    public void onClick(View v){
        //if(v.getId() == R.id.buttonStartGame){
        //
        //}
        if(rbOnePlayer.isChecked()){
            tvPlayerTwo.setAlpha(1);
        }

    }

}
