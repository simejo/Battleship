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
import a8.battleship.Logic.Variables;
import a8.battleship.Models.AiPlayer;
import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.R;

/**
 * This is the view where a player can choose between one-player or two-players
 * The player and the board will be initialized here
 */
public class StartView extends ActionBarActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    private RadioButton rbOnePlayer, rbTwoPlayer;
    private Button buttonStartGame;
    private TextView tvPlayerOne, tvPlayerTwo, tvEasyAI, tvMediumAI, tvHardAI, tvDifficulty;
    private SeekBar sbAIChooser;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
     */
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


    /**
     * The onClick method is called when one of its observers (buttons) are pushed.
     * @param v The source view which is pushed.
     */
    public void onClick(View v){

        /**
         * If this button is pressed, the user has decided if he/she wants one player or two player
         * and the players is created
         * The boards will be initialized here.
         */
        if(v.getId() == R.id.buttonStartGame){
            //Creates Player One
            if(tvPlayerOne.getText().toString().equals("")){
                Variables.playerOne = new Player("Player 1");
            }
            else{
                Variables.playerOne = new Player(tvPlayerOne.getText().toString());
            }
            Variables.playerOne.setBoard(new Board(Variables.boardSize));

            Variables.turn = "playerOne";

            //Creates player two
            if(rbTwoPlayer.isChecked()){
                if(tvPlayerTwo.getText().toString().equals("")){
                    Variables.playerTwo = new Player("Player 2");
                }
                else{
                    Variables.playerTwo = new Player(tvPlayerTwo.getText().toString());
                }
                Variables.playerTwo.setBoard(new Board(Variables.boardSize));
                Variables.gameMode = "twoPlayer";
                Variables.turn = "playerTwo";
            }

            else{
                //Creates player AI
                Variables.playerAI = new AiPlayer();
                Variables.playerAI.setBoard(new Board(Variables.boardSize));
                Variables.gameMode = "onePlayer";
                if(Variables.playerAI.getLevel().equals("easy")){
                    Variables.playerAI.setName("Per");
                } else if(Variables.playerAI.getLevel().equals("medium")){
                    Variables.playerAI.setName("Pål");
                } else{
                    Variables.playerAI.setName("Askeladd");
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

    /**
     * Need this method because it's a part of the interface, but we don't use it.
     * @param seekBar
     * @param progress
     * @param fromUser
     */
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
            Variables.level = "easy";
        }else if(seekBar.getProgress() == 1){
            Variables.level = "medium";
        }else{
            Variables.level = "hard";
        }
    }
}
