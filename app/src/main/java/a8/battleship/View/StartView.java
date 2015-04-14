package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
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
public class StartView extends ActionBarActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private RadioButton rbOnePlayer, rbTwoPlayer;
    private Button buttonStartGame;
    private TextView tvPlayerOne, tvPlayerTwo, tvEasyAI, tvMediumAI, tvHardAI, tvDifficulty;
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
        tvDifficulty = (TextView) findViewById(R.id.textViewDifficulty);

        rbOnePlayer.setOnClickListener(this);
        rbTwoPlayer.setOnClickListener(this);
        rbOnePlayer.setChecked(true);
        buttonStartGame.setOnClickListener(this);

        tvPlayerTwo.setVisibility(View.INVISIBLE);

        sbAIChooser.setMax(2);
        sbAIChooser.setProgress(0);

        sbAIChooser.setOnSeekBarChangeListener(this);

    }
    public void onClick(View v){

        //Functionality for button
        if(v.getId() == R.id.buttonStartGame){

            //Creates players
            //Creates Player One
            if(tvPlayerOne.getText().toString().equals("")){
                Constants.playerOne = new Player("Player 1");
            }
            else{
                Constants.playerOne = new Player(tvPlayerOne.getText().toString());
            }
            Constants.playerOne.setBoard(new Board(Constants.boardSize));

            Constants.turn = "playerOne";

            //Creates player two
            if(rbTwoPlayer.isChecked()){

                if(tvPlayerTwo.getText().toString().equals("")){
                    Constants.playerTwo = new Player("Player 2");
                }
                else{
                    Constants.playerTwo = new Player(tvPlayerTwo.getText().toString());
                }
                Constants.playerTwo.setBoard(new Board(Constants.boardSize));
                Constants.gameMode = "twoPlayer";
                Constants.turn = "playerTwo";
            }

            else{
                //Creates player AI
                Constants.playerAI = new AiPlayer();
                Constants.playerAI.setBoard(new Board(Constants.boardSize));
                Constants.gameMode = "onePlayer";
                if(Constants.playerAI.getLevel().equals("low")){
                    Constants.playerAI.setName("Per");
                } else if(Constants.playerAI.getLevel().equals("medium")){
                    Constants.playerAI.setName("Pål");
                } else{
                    Constants.playerAI.setName("Askeladd");
                }
            }
            startActivity(new Intent(StartView.this, SetShipView.class));
        }

        //Functionality for radio buttons
        if(rbTwoPlayer.isChecked()) {
            tvPlayerTwo.setVisibility(View.VISIBLE);
            tvDifficulty.setVisibility(View.INVISIBLE);
            sbAIChooser.setVisibility(View.INVISIBLE);
            tvEasyAI.setVisibility(View.INVISIBLE);
            tvMediumAI.setVisibility(View.INVISIBLE);
            tvHardAI.setVisibility(View.INVISIBLE);

        }
        if(rbOnePlayer.isChecked()){
            tvPlayerTwo.setVisibility(View.INVISIBLE);
            tvDifficulty.setVisibility(View.VISIBLE);
            sbAIChooser.setVisibility(View.VISIBLE);
            tvEasyAI.setVisibility(View.VISIBLE);
            tvMediumAI.setVisibility(View.VISIBLE);
            tvHardAI.setVisibility(View.VISIBLE);
        }


    }
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

    }

    /**
     * Notification that the user has started a touch gesture. Clients may want to use this
     * to disable advancing the seekbar.
     * @param seekBar The SeekBar in which the touch gesture began
     */
    public void onStartTrackingTouch(SeekBar seekBar){

    }

    /**
     * Notification that the user has finished a touch gesture. Clients may want to use this
     * to re-enable advancing the seekbar.
     * @param seekBar The SeekBar in which the touch gesture began
     */
    public void onStopTrackingTouch(SeekBar seekBar){
        if(seekBar.getProgress() == 0){
            Constants.level = "low";
        }else if(seekBar.getProgress() == 1){
            Constants.level = "medium";
        }else
            Constants.level = "hard";
        {

        }
    }
}
