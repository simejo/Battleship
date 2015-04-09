package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import a8.battleship.Logic.Constants;
import a8.battleship.Models.AiPlayer;
import a8.battleship.R;
import android.widget.TextView;

/**
 * Created by Kartefull on 11.03.2015.
 */
public class SwitchView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonSwitchPlayer;
    private TextView switchViewHeader;
    //private AiPlayer playerAI = Constants.playerAI;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_view);

        buttonSwitchPlayer = (Button)findViewById(R.id.buttonSwitchPlayer);

        buttonSwitchPlayer.setOnClickListener(this);

        switchViewHeader = (TextView)findViewById(R.id.switchViewHeader);
        if(Constants.turn == "playerOne"){
            switchViewHeader.setText(Constants.playerOne.getName() + "'s turn");
        }
        else if(Constants.turn == "playerTwo"){
            switchViewHeader.setText(Constants.playerTwo.getName() + "'s turn");
        }
        else if(Constants.turn == "playerAI"){
            switchViewHeader.setText("Player AI's turn");
        }
    }
    public void onClick(View v){
        //Functionality for button
        if(v.getId() == R.id.buttonSwitchPlayer){
            //If its twoPlayer
            if(Constants.turn == "playerOne" && Constants.gameMode == "twoPlayer"){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else if (Constants.turn == "playerTwo" && Constants.gameMode == "twoPlayer"){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            //If its onePlayer ---
            if(Constants.turn == "playerOne" && Constants.gameMode == "onePlayer"){

                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else if (Constants.turn == "playerAi" && Constants.gameMode == "onePlayer"){

                startActivity(new Intent(SwitchView.this, BattleView.class));
                Constants.turn = "playerOne";



                //startActivity(new Intent(SwitchView.this, BattleView.class));
            }

        }




    }
}
