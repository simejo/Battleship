package a8.battleship.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import a8.battleship.Logic.Constants;
import a8.battleship.Models.Player;
import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class StartView extends ActionBarActivity implements View.OnClickListener{

    //Choose between one-player or two players

    private RadioButton rbOnePlayer, rbTwoPlayer;
    private Button buttonStartGame;
    private TextView tvPlayerOne, tvPlayerTwo;
    private SeekBar sbAIChooser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        rbOnePlayer = (RadioButton) findViewById(R.id.radioButtonOnePlayer);
        rbTwoPlayer = (RadioButton) findViewById(R.id.radioButtonTwoPlayer);
        buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        tvPlayerOne = (EditText) findViewById(R.id.tvPlayerOne);
        tvPlayerTwo = (EditText) findViewById(R.id.tvPlayerTwo);
        sbAIChooser = (SeekBar) findViewById(R.id.seekBarAIChooser);

        rbOnePlayer.setOnClickListener(this);
        rbTwoPlayer.setOnClickListener(this);
        rbOnePlayer.setChecked(true);
        buttonStartGame.setOnClickListener(this);

        tvPlayerOne.setAlpha(0);
        tvPlayerTwo.setAlpha(0);

    }
    public void onClick(View v){

        //Functionality for button
        if(v.getId() == R.id.buttonStartGame){
            startActivity(new Intent(StartView.this, SetShipView.class));
            Constants.playerOne = new Player();
        }

        //Functionality for radiobuttons
        if(rbTwoPlayer.isChecked()) {
            tvPlayerOne.setAlpha(1);
            tvPlayerTwo.setAlpha(1);
            sbAIChooser.setAlpha(0);

        }
        if(rbOnePlayer.isChecked()){
            tvPlayerOne.setAlpha(0);
            tvPlayerTwo.setAlpha(0);
            sbAIChooser.setAlpha(1);
        }




    }

}
