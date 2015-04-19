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

/**
 * This class is responsible for the switch between playerOne and playerTwo.
 * When playerOne has shot, confirmed shot, and pressed next player, a switch view will appear
 * to make sure that the other player wont be able to see playerOne's board, and vice versa.
 */
public class SwitchView extends ActionBarActivity implements View.OnClickListener{

    private Button buttonSwitchPlayer;
    private TextView switchViewHeader, textViewStatus;

    /**
     * onCreate is called when the class is shown. Here we initialize all objects and references and set listeners to the widgets
     * @param savedInstanceState If you save the state of the application in a bundle, it can be passed
     *                           back to onCreate if the activity needs to be recreated so that you don't
     *                           lose this prior information. If no data was supplied, savedInstanceState is null.
     */
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

    /**
     * The onClick method is called when one of its observers (buttons) are pushed.
     * @param v The source view which is pushed.
     */
    public void onClick(View v){
        if(v.getId() == R.id.buttonSwitchPlayer){
            //If its twoPlayer
            if (Variables.turn.equals("playerAI") && Variables.gameMode.equals("onePlayer")){
                Variables.turn = "playerOne";
            }
            Intent i = new Intent(SwitchView.this, BattleView.class);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
