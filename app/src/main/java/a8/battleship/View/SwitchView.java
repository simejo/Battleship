package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import a8.battleship.Logic.Variables;
import a8.battleship.R;
import android.widget.TextView;

public class SwitchView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonSwitchPlayer;
    private TextView switchViewHeader, textViewStatus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_view);

        buttonSwitchPlayer = (Button)findViewById(R.id.buttonSwitchPlayer);
        textViewStatus = (TextView)findViewById(R.id.textViewStatus);
        textViewStatus.setText(Variables.stringStatus);

        buttonSwitchPlayer.setOnClickListener(this);

        switchViewHeader = (TextView)findViewById(R.id.switchViewHeader);
        if(Variables.gameMode.equals("onePlayer")){
            switchViewHeader.setText(Variables.playerOne.getName() + "'s turn");
        }
        else{
            switch(Variables.turn){
                case("playerOne"):
                    switchViewHeader.setText(Variables.playerOne.getName() + "'s turn");
                    break;
                case("playerTwo"):
                    switchViewHeader.setText(Variables.playerTwo.getName() + "'s turn");
                    break;
                case("playerAI"):
                    switchViewHeader.setText("Player AI's turn");
                    break;
            }
        }
    }
    public void onClick(View v){
        //Functionality for button
        if(!Variables.backgroundMusic.isPlaying()){
            Variables.backgroundMusic.isLooping();
        }
        if(v.getId() == R.id.buttonSwitchPlayer){
            //If its twoPlayer
            if(Variables.turn.equals("playerOne") && Variables.gameMode.equals("twoPlayer")){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else if (Variables.turn.equals("playerTwo") && Variables.gameMode.equals("twoPlayer")){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            //If its onePlayer ---
            if(Variables.turn.equals("playerOne") && Variables.gameMode.equals("onePlayer")){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else if (Variables.turn.equals("playerAI") && Variables.gameMode.equals("onePlayer")){
                startActivity(new Intent(SwitchView.this, BattleView.class));
                Variables.turn = "playerOne";
            }
        }




    }
}
