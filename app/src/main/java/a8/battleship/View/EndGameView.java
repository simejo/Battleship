package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import a8.battleship.Logic.Variables;
import a8.battleship.R;

/**
 * EndGameView contains all functionality needed when the battle is over and a player has won
 */
public class EndGameView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonMainMenu;
    private TextView tvEndGameHeader, tvEndScore;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
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
     * The onClick method is called when one of its observers (buttons) are pushed.
     * @param v The source view which is pushed.
     */
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMainMenu){
            Intent i = new Intent(EndGameView.this, MainMenuView.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
