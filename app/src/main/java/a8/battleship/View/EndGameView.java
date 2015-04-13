package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import a8.battleship.Logic.Constants;
import a8.battleship.R;

/*
 * Created by Kartefull on 11.03.2015.
 */
public class EndGameView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonMainMenu;
    private TextView tvEndGameHeader, tvEndScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_view);

        tvEndGameHeader = (TextView) findViewById(R.id.textViewCongratulations);
        tvEndScore = (TextView) findViewById(R.id.textViewEndScore);
        buttonMainMenu = (Button)findViewById(R.id.buttonMainMenu);
        buttonMainMenu.setOnClickListener(this);

        if(Constants.gameMode.equals("onePlayer")){
            tvEndScore.setText(Constants.playerOne.getName() + " score: " + Constants.playerOne.getScore() + "\n " + Constants.playerAI.getName() + " score: " + Constants.playerAI.getScore());
        } else {
            tvEndScore.setText(Constants.playerOne.getName() + " score: " + Constants.playerOne.getScore() + "\n " + Constants.playerTwo.getName() + " score: " + Constants.playerTwo.getScore());
        }

        tvEndGameHeader.setText(Constants.winner + " won");

    }

    public void onClick(View v) {
        if(v.getId() == R.id.buttonMainMenu){
            startActivity(new Intent(EndGameView.this, MainMenuView.class));
        }
    }
}
