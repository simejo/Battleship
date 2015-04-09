package a8.battleship.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import a8.battleship.Logic.Constants;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;

/**
 * Created by Kartefull on 11.03.2015.
 * This is the view where a player can choose between one-player or two-players
 * The player and the board will be initialized here
 */
public class StartView extends ActionBarActivity implements View.OnClickListener{

    private RadioButton rbOnePlayer, rbTwoPlayer;
    private Button buttonStartGame;
    private TextView tvPlayerOne, tvPlayerTwo, tvEasyAI, tvMediumAI, tvHardAI;
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
        tvEasyAI = (TextView) findViewById(R.id.textViewEasyAI);
        tvMediumAI = (TextView) findViewById(R.id.textViewMediumAI);
        tvHardAI = (TextView) findViewById(R.id.textViewHardAI);

        rbOnePlayer.setOnClickListener(this);
        rbTwoPlayer.setOnClickListener(this);
        rbOnePlayer.setChecked(true);
        buttonStartGame.setOnClickListener(this);

        tvPlayerTwo.setAlpha(0);

        sbAIChooser.setMax(2);
        sbAIChooser.setProgress(1);

    }
    public void onClick(View v){

        //Functionality for button
        if(v.getId() == R.id.buttonStartGame){
            //TODO: Check if textView is empty or not

            //Creates players

            if(rbTwoPlayer.isChecked()){

                //Creates player two
                Constants.playerTwo = new Player(tvPlayerTwo.getText().toString());
                Constants.playerTwo.setBoard(new Board(Constants.boardSize));
                Constants.gameMode = "twoPlayer";
            }

            else{
                //Creates player AI
                Constants.playerAI = new AiPlayer();
                Constants.playerAI.setBoard(new Board(Constants.boardSize));
                Constants.gameMode = "onePlayer";
            }

            //Creates Player One
            Constants.playerOne = new Player(tvPlayerOne.getText().toString());
            Constants.playerOne.setBoard(new Board(Constants.boardSize));

            Constants.turn = "playerOne";

            startActivity(new Intent(StartView.this, SetShipView.class));
        }

        //Functionality for radiobuttons
        if(rbTwoPlayer.isChecked()) {
            tvPlayerOne.setAlpha(1);
            tvPlayerTwo.setAlpha(1);
            sbAIChooser.setAlpha(0);
            tvEasyAI.setAlpha(0);
            tvMediumAI.setAlpha(0);
            tvHardAI.setAlpha(0);

        }
        if(rbOnePlayer.isChecked()){
            tvPlayerTwo.setAlpha(0);
            sbAIChooser.setAlpha(1);
            tvEasyAI.setAlpha(1);
            tvMediumAI.setAlpha(1);
            tvHardAI.setAlpha(1);
        }


    }

}
