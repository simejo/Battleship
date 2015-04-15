package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import a8.battleship.Logic.Variables;
import a8.battleship.R;

/*
 * Created by Kartefull on 11.03.2015.
 */

/**
 * EndGameView contains all functionality needed when the battle is over and a player has won
 */
public class EndGameView extends ActionBarActivity implements View.OnClickListener{

    /**
     * @param buttonMainMenu is a button leading back to main menu
     * @param tvEndGameHeader is congratulating text displayed on end screen
     * @param tvEndScore is text displaying score at the end of the game
     */
    private Button buttonMainMenu;
    private TextView tvEndGameHeader, tvEndScore;

    /**
     * onCreate makes a new view, and gives each variable correct value, and displays correct text.
     * In addition, sets click listeners
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_view);

        tvEndGameHeader = (TextView) findViewById(R.id.textViewCongratulations);
        tvEndScore = (TextView) findViewById(R.id.textViewEndScore);
        buttonMainMenu = (Button)findViewById(R.id.buttonMainMenu);
        buttonMainMenu.setOnClickListener(this);

        if(Variables.gameMode.equals("onePlayer")){
            tvEndScore.setText(Variables.playerOne.getName() + " score: " + Variables.playerOne.getScore() + "\n " + Variables.playerAI.getName() + " score: " + Variables.playerAI.getScore());
        } else {
            tvEndScore.setText(Variables.playerOne.getName() + " score: " + Variables.playerOne.getScore() + "\n " + Variables.playerTwo.getName() + " score: " + Variables.playerTwo.getScore());
        }
        tvEndGameHeader.setText(Variables.winner + " won");

    }

    /**
     * if button is clicked, return to main menu
     */
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMainMenu){
            startActivity(new Intent(EndGameView.this, MainMenuView.class));
        }
    }
}
