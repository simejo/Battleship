package a8.battleship.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import a8.battleship.Logic.Constants;
import a8.battleship.R;
import android.widget.TextView;

public class SwitchView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonSwitchPlayer;
    private TextView switchViewHeader, textViewStatus;
    //private AiPlayer playerAI = Constants.playerAI;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_view);

        buttonSwitchPlayer = (Button)findViewById(R.id.buttonSwitchPlayer);
        textViewStatus = (TextView)findViewById(R.id.textViewStatus);
        textViewStatus.setText(Constants.stringStatus);

        buttonSwitchPlayer.setOnClickListener(this);

        switchViewHeader = (TextView)findViewById(R.id.switchViewHeader);
        if(Constants.gameMode.equals("onePlayer")){
            switchViewHeader.setText(Constants.playerOne.getName() + "'s turn");
        }
        else{
            switch(Constants.turn){
                case("playerOne"):
                    switchViewHeader.setText(Constants.playerOne.getName() + "'s turn");
                    break;
                case("playerTwo"):
                    switchViewHeader.setText(Constants.playerTwo.getName() + "'s turn");
                    break;
                case("playerAI"):
                    switchViewHeader.setText("Player AI's turn");
                    break;
            }
        }
    }
    public void onClick(View v){
        //Functionality for button
        if(!Constants.backgroundMusic.isPlaying()){
            Constants.backgroundMusic.isLooping();
        }
        if(v.getId() == R.id.buttonSwitchPlayer){
            Log.i("SwitchView.java","When buttonSwitchPlayer is pressed - the current player is: " + Constants.turn);
            //If its twoPlayer
            if(Constants.turn.equals("playerOne") && Constants.gameMode.equals("twoPlayer")){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else if (Constants.turn.equals("playerTwo") && Constants.gameMode.equals("twoPlayer")){
                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            //If its onePlayer ---
            if(Constants.turn.equals("playerOne") && Constants.gameMode.equals("onePlayer")){

                startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            else if (Constants.turn.equals("playerAI") && Constants.gameMode.equals("onePlayer")){

                startActivity(new Intent(SwitchView.this, BattleView.class));
                Constants.turn = "playerOne";
                //startActivity(new Intent(SwitchView.this, BattleView.class));
            }
            Log.i("SwitchView.java","When buttonSwitchPlayer logic is done - the current player is: " + Constants.turn);


        }




    }
}
